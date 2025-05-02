package ATMApplication;

public class ATMApplication {
	public static void main(String[] args) {
		ATMUserDetails atm = new ATMUserDetails();
		ATMServerConnection server = new ATMServerConnection();
		ATMConsoleUI ui = new ATMConsoleUI(atm, server);
		ui.start();
	}
}
