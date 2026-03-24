/**
 * Checking Account class
 */
public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 100.0; // overdraft limit
    
    public CheckingAccount(String accountNumber, double initialBalance, User owner) {
        super(accountNumber, initialBalance, owner);
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw negative amount");
        }
        if (amount > balance + OVERDRAFT_LIMIT) {
            System.err.println("Error: Exceeds balance and overdraft limit");
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
    
    @Override
    public void displayAccount() {
        System.out.println("Account Type: Checking Account");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Balance: " + balance);
        System.out.println("Overdraft Limit: " + OVERDRAFT_LIMIT);
    }
    
    @Override
    public String getAccountType() {
        return "Checking Account";
    }
}
