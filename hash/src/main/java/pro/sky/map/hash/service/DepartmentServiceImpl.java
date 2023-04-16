package pro.sky.map.hash.service;

import org.springframework.stereotype.Service;
import pro.sky.map.hash.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override

    public Employee getEmployeeWithMaxSalary(Integer departmentId) {

        Collection<Employee> employees = employeeService.getAllEmployees();

        return employees.stream()
                .filter(it -> Objects.equals(it.getDepartment(), departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        Collection<Employee> employees = employeeService.getAllEmployees();

        return employees.stream()
                .filter(it -> Objects.equals(it.getDepartment(), departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .get();
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Integer departmentId) {
        Collection<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(it -> Objects.equals(it.getDepartment(), departmentId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesCollectedByDepartment() {
        Collection<Employee> employees = employeeService.getAllEmployees();

        return employees.stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
