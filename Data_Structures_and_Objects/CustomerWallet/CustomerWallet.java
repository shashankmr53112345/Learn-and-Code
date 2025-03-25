package CustomerWallet;

public class CustomerWallet {
	private float balance;

	public CustomerWallet(float initialBalance) {
		this.balance = initialBalance;
	}

	public float getBalance() {
		return balance;
	}

	public void deposit(float amountToDeposit) {
		balance += amountToDeposit;
	}

	public boolean withdraw(float amountToWithdraw) {
		if (balance >= amountToWithdraw) {
			balance -= amountToWithdraw;
			return true;
		}
		return false;
	}
}
