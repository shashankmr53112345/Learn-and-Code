package ATMApplication;

import java.util.Scanner;

public class ATMConsoleUI {
	private ATMUserDetails atm;
	private ATMServerConnection server;
	private Scanner scanner = new Scanner(System.in);

	public ATMConsoleUI(ATMUserDetails atm, ATMServerConnection server) {
		this.atm = atm;
		this.server = server;
	}

	public void start() {
		if (!server.isConnected()) {
			System.out.println("Unable to connect to server.");
			return;
		}

		try {
			if (!login())
				return;
			showMenu();
		} catch (CardBlockedException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean login() throws CardBlockedException {
		for (int i = 0; i < 3; i++) {
			System.out.print("Enter 4-digit PIN: ");
			String pin = scanner.nextLine();
			if (atm.validatePin(pin)) {
				System.out.println("Login successful.");
				return true;
			} else {
				System.out.println("Invalid PIN.");
			}
		}
		return false;
	}

	private void showMenu() {
		int choice;
		do {
			System.out.println("\nATM MENU:\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			try {
				switch (choice) {
				case 1 -> System.out.println("Balance: " + atm.getAccountBalance());
				case 2 -> {
					System.out.print("Enter deposit amount: ");
					double depositAmt = scanner.nextDouble();
					atm.deposit(depositAmt);
					System.out.println("Deposited.");
				}
				case 3 -> {
					System.out.print("Enter withdraw amount: ");
					double withdrawAmt = scanner.nextDouble();
					atm.withdraw(withdrawAmt);
					System.out.println("Withdrawn.");
				}
				case 4 -> System.out.println("Thank you.");
				default -> System.out.println("Invalid choice.");
				}
			} catch (ATMException e) {
				System.out.println(e.getMessage());
			}
		} while (choice != 4);
	}
}
