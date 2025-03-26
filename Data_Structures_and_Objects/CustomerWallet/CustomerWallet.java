package CustomerWallet;

public class CustomerWallet {
	private String firstName;
	private String lastName;
	private float balance;
	private static final float MINIMUM_BALANCE = 0.0f;

	public CustomerWallet(String firstName, String lastName, float initialBalance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = initialBalance;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public float checkBalance() {
		return balance;
	}

	public void deposit(float amountToDeposit) {
		if (amountToDeposit > 0) {
			this.balance += amountToDeposit;
		}
	}

	public boolean withdraw(float amountToWithdraw) {
		if (amountToWithdraw > 0 && (this.balance - amountToWithdraw) >= MINIMUM_BALANCE) {
			this.balance -= amountToWithdraw;
			return true;
		}
		return false;
	}
}
