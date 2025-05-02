package ATMApplication;

public class ATMUserDetails {
	private static final String HARDCODED_PIN = "2105";
	private static final double DAILY_WITHDRAWAL_LIMIT = 20000.0;
	private static final double INITIAL_ATM_CASH = 20000.0;

	private String pin = HARDCODED_PIN;
	private int invalidAttempts = 0;
	private double accountBalance = 10000.0;
	private double atmCash = INITIAL_ATM_CASH;
	private double dailyWithdrawn = 0.0;
	private final int MAX_INVALID_ATTEMPTS = 3;

	public boolean validatePin(String inputPin) throws CardBlockedException {
		if (inputPin.equals(pin)) {
			invalidAttempts = 0;
			return true;
		} else {
			invalidAttempts++;
			if (invalidAttempts >= MAX_INVALID_ATTEMPTS) {
				throw new CardBlockedException();
			}
			return false;
		}
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void deposit(double amount) throws InvalidAmountException {
		if (amount <= 0)
			throw new InvalidAmountException();
		accountBalance += amount;
		atmCash += amount;
	}

	public void withdraw(double amount) throws ATMException {
		if (amount <= 0)
			throw new InvalidAmountException();
		if (dailyWithdrawn + amount > DAILY_WITHDRAWAL_LIMIT)
			throw new DailyLimitExceededException();
		if (amount > accountBalance)
			throw new InsufficientFundsException();
		if (amount > atmCash)
			throw new ATMInsufficientCashException();

		accountBalance -= amount;
		atmCash -= amount;
		dailyWithdrawn += amount;
	}
}
