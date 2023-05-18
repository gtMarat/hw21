package pro.sky.map.hash;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.map.hash.exception.EmployeeAlreadyAddedException;
import pro.sky.map.hash.exception.EmployeeNotFoundException;
import pro.sky.map.hash.model.Employee;
import pro.sky.map.hash.service.EmployeeService;
import pro.sky.map.hash.service.EmployeeServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.sky.map.hash.EmployeeServiceImplTestConstance.*;

public class EmployeeServiceTest {
    private EmployeeService out;

    @BeforeEach
    void setUp() {
        out = new EmployeeServiceImpl();
    }

    @Test
    public void testAdd() {
        Employee actualEmployee = out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testAddDuplicate() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Assertions.assertThrowsExactly(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1));
    }

    @Test
    void testRemoveAddedEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee actualEmployee = out.removeEmployee(FIRST_NAME_1, LAST_NAME_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testRemoveNonExistentEmployee() {
        Assertions.assertThrowsExactly(EmployeeNotFoundException.class, () -> out.removeEmployee(FIRST_NAME_1, LAST_NAME_1));
    }
    @Test
    void testFindAddedEmployee() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        Employee actualEmployee = out.findEmployee(FIRST_NAME_1, LAST_NAME_1);
        Employee expectedEmployee = new Employee(FIRST_NAME_1, LAST_NAME_1);
        assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    void testFindNonExistentEmployee() {
        Assertions.assertThrowsExactly(EmployeeNotFoundException.class, () -> out.findEmployee(FIRST_NAME_1, LAST_NAME_1));
    }


    @Test
    void testGetAll() {
        out.addEmployee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        out.addEmployee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2);
        Collection<Employee> actual = out.getAllEmployees();
        List<Employee> expected = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_2, SALARY_2)
        );

        assertEquals(actual.size(), expected.size());
        Assertions.assertTrue(expected.containsAll(actual));
    }
}


