package pro.sky.map.hash.service;

import org.springframework.stereotype.Service;
import pro.sky.map.hash.exception.EmployeeAlreadyAddedException;
import pro.sky.map.hash.exception.EmployeeNotFoundException;
import pro.sky.map.hash.model.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }


        public boolean existsEmployee (Employee employee){
            return (getEmployee(employee) != null);
        }

        private Employee getEmployee (Employee employee){
            if (!employees.containsKey(employee.toString())) {
                return null;
            }
            return employees.get(employee.toString());
        }


        @Override
        public Employee addEmployee (String firstName, String lastName, Integer department, Double salary){
            Employee employee = new Employee(firstName, lastName, department, salary);
            if (existsEmployee(employee))
                throw new EmployeeAlreadyAddedException();
            employees.put(employee.toString(), employee);
            return employee;
        }


    @Override
        public Employee removeEmployee (String firstName, String lastName){

            Employee employee = findEmployee(firstName, lastName);
            if (!existsEmployee(employee)) {
                throw new EmployeeNotFoundException();
            }
            employees.remove(employee.toString());
            return employee;
        }

        @Override
        public Employee findEmployee (String firstName, String lastName){
            Employee employee = new Employee(firstName, lastName);
            if (!existsEmployee(employee)) {
                throw new EmployeeNotFoundException();
            }
            return employee;
        }

        @Override
        public Collection <Employee> getAllEmployees () {
            return  Collections.unmodifiableCollection(employees.values());
        }
    }
