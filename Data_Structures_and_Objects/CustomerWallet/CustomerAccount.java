package CustomerWallet;

public class CustomerAccount {
    private String firstName;
    private String lastName;
    private CustomerWallet wallet;

    public CustomerAccount(String firstName, String lastName, float initialBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wallet = new CustomerWallet(initialBalance);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean processPayment(float amountToWithdraw) {
        return wallet.withdraw(amountToWithdraw);
    }

    public void depositMoney(float amountToDeposit) {
        wallet.deposit(amountToDeposit);
    }

    public float checkWalletBalance() {
        return wallet.getBalance();
    }
}

