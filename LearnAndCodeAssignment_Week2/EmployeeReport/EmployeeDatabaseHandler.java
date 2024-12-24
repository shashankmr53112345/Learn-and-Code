package NumberGuessingGame;

public class EmployeeDatabaseHandler {
    public void saveEmployee(EmployeeDetails employee) {
        System.out.println("Saving employee: " + employee.getName() + " to the database.");
    }

    public void terminateEmployee(EmployeeDetails employee) {
        employee.setWorking(false);
        System.out.println("Employee " + employee.getName() + " has been terminated.");
    }
}
