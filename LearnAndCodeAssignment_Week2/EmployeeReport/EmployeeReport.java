package NumberGuessingGame;

public class EmployeeReport {
    public static void main(String[] args) {
        EmployeeDetails employee = new EmployeeDetails(1, "John Doe", "IT", true);

        EmployeeDatabaseHandler databaseHandler = new EmployeeDatabaseHandler();
        databaseHandler.saveEmployee(employee);

        EmployeeReportGenerator reportGenerator = new EmployeeReportGenerator();
        reportGenerator.generateXMLReport(employee);
        reportGenerator.generateCSVReport(employee);

        databaseHandler.terminateEmployee(employee);

        reportGenerator.generateXMLReport(employee);
        reportGenerator.generateCSVReport(employee);
    }
}

