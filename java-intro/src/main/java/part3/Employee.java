package part3;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {

    private String lastName;
    private String position;
    private double salary;
    private int yearOfBorn;

    public Employee(){};

    public Employee(String lastName, String position, double salary, int yearOfBorn) {
        setLastName(lastName);
        setPosition(position);
        setSalary(salary);
        setYearOfBorn(yearOfBorn);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("LastName is invalid");
        }
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Position is invalid");
        }
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary is invalid");
        }
        this.salary = salary;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        if (yearOfBorn <= 0) {
            throw new IllegalArgumentException("Year Of Born is invalid");
        }
        this.yearOfBorn = yearOfBorn;
    }

    public void setPremium() {
        setSalary(salary + salary * 0.15);
    }

    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse("01.01." + yearOfBorn, formatter);
        LocalDate now = LocalDate.now();
        Period period = Period.between(startDate, now);
        return period.getYears();
    }

}
