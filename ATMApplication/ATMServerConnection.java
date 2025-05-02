package ATMApplication;

import java.util.Random;

public class ATMServerConnection {
	private Random random = new Random();

	public boolean isConnected() {
		return random.nextInt(10) != 0;
	}
}
