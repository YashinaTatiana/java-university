package part3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    Employee testEmployee;

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectLastName() {
        testEmployee = new Employee(null, "engineer", 50000, 1994);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectPosition() {
        testEmployee = new Employee("Lubavin", "", 50000, 1994);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectSalary() {
        testEmployee = new Employee("Lubavin", "engineer", -2, 1994);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectYear() {
        testEmployee = new Employee("Lubavin", "engineer", 50000, -20);
    }

    @Test
    public void testGetPremium() {
        testEmployee = new Employee("Lubavin", "engineer", 50000, 1994);
        testEmployee.setPremium();
        assertEquals( 57500, testEmployee.getSalary(), 0.01);
    }

    @Test
    public void testGetAge() {
        testEmployee = new Employee("Lubavin", "engineer", 50000, 1994);
        assertEquals( 26, testEmployee.getAge());
    }

}
