package pro.sky.map.hash.service;

import pro.sky.map.hash.model.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Map<String , Employee> getAllEmployees();
}
