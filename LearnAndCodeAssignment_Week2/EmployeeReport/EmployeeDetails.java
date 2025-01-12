package NumberGuessingGame;

public class EmployeeDetails {
    private int id;
    private String name;
    private String department;
    private boolean isWorking;

    public EmployeeDetails(int id, String name, String department, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.isWorking = isWorking;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "', isWorking=" + isWorking + '}';
    }
}
