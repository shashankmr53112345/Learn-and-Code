package NumberGuessingGame;

public class EmployeeReportGenerator {
    public void generateXMLReport(EmployeeDetails employee) {
        // Logic to generate employee report in XML format
        System.out.println("<employee>");
        System.out.println("    <id>" + employee.getId() + "</id>");
        System.out.println("    <name>" + employee.getName() + "</name>");
        System.out.println("    <department>" + employee.getDepartment() + "</department>");
        System.out.println("    <isWorking>" + employee.isWorking() + "</isWorking>");
        System.out.println("</employee>");
    }

    public void generateCSVReport(EmployeeDetails employee) {
        // Logic to generate employee report in CSV format
        System.out.println("ID,Name,Department,IsWorking");
        System.out.println(employee.getId() + "," + employee.getName() + "," + employee.getDepartment() + "," + employee.isWorking());
    }
}
