package ATMApplication;

public class ATMException extends Exception {
	public ATMException(String message) {
		super(message);
	}
}

class CardBlockedException extends ATMException {
	public CardBlockedException() {
		super("Card blocked due to 3 invalid PIN attempts.");
	}
}

class InsufficientFundsException extends ATMException {
	public InsufficientFundsException() {
		super("Insufficient account balance.");
	}
}

class ATMInsufficientCashException extends ATMException {
	public ATMInsufficientCashException() {
		super("ATM has insufficient cash.");
	}
}

class DailyLimitExceededException extends ATMException {
	public DailyLimitExceededException() {
		super("Daily withdrawal limit exceeded.");
	}
}

class InvalidAmountException extends ATMException {
	public InvalidAmountException() {
		super("Amount must be greater than zero.");
	}
}
