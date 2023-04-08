package pro.sky.map.hash.service;

import org.springframework.stereotype.Service;
import pro.sky.map.hash.exception.EmployeeAlreadyAddedException;
import pro.sky.map.hash.exception.EmployeeNotFoundException;
import pro.sky.map.hash.model.Employee;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFirstName()+employee.getLastName(), employee);
        return employee;

    }
    public boolean existsEmployee(Employee employee) {
        return (getEmployee(employee) != null);
    }

    private Employee getEmployee(Employee employee) {
        if (!employees.containsKey(employee.toString())) {
            return null;
        }
        return employees.get(employee.toString());
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        Employee employee = findEmployee(firstName, lastName);
        if (!existsEmployee(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee.toString());
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!existsEmployee(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employees;
    }


}
