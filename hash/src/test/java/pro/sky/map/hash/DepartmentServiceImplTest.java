package pro.sky.map.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.map.hash.model.Employee;
import pro.sky.map.hash.service.DepartmentServiceImpl;
import pro.sky.map.hash.service.EmployeeService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.sky.map.hash.EmployeeServiceImplTestConstance.*;
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeServiceMock;
    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2)
        ));
    }

    @Test
    public void testMinSalary() {
        Employee actualResult = out.getEmployeeWithMinSalary(DEPARTMENT_1);
        Employee expectedResult = new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void testMaxSalary() {
        Employee actualResult = out.getEmployeeWithMaxSalary(DEPARTMENT_1);
        Employee expectedResult = new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetEmployeesDepartment() {
        Collection<Employee> actual = out.getEmployeesByDepartment(DEPARTMENT_1);
        List<Employee> expected = List.of(
                new Employee(FIRST_NAME_1, LAST_NAME_1, DEPARTMENT_1, SALARY_1),
                new Employee(FIRST_NAME_2, LAST_NAME_2, DEPARTMENT_1, SALARY_2)
        );

        assertEquals(actual.size(), expected.size());
        Assertions.assertTrue(expected.containsAll(actual));
    }
}
