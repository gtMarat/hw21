package pro.sky.map.hash.service;

import pro.sky.map.hash.model.Employee;

import java.util.Collection;


public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Double salary);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAllEmployees();
}
