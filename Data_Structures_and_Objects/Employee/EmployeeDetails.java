package Employee;

public class EmployeeDetails {
	private String name;
	private int age;
	private float salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}

/*
 * The Employee class is a data structure because it only holds data (name, age,
 * salary) and provides getters and setters without meaningful behavior. It
 * exposes its internal state, allowing external code to manipulate it directly,
 * following a procedural approach rather than true object-oriented
 * encapsulation and behavior-driven design.
 */
