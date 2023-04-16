package pro.sky.map.hash.service;

import pro.sky.map.hash.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Employee getEmployeeWithMinSalary(Integer departmentId);

    List<Employee> getEmployeesByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> getEmployeesCollectedByDepartment();


}
