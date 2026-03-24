/**
 * Savings Account class
 */
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02; // 2% annual interest rate
    
    public SavingsAccount(String accountNumber, double initialBalance, User owner) {
        super(accountNumber, initialBalance, owner);
    }
    
    /**
     * Apply interest
     */
    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest applied: " + interest);
    }
    
    @Override
    public void displayAccount() {
        System.out.println("Account Type: Savings Account");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Balance: " + balance);
        System.out.println("Annual Interest Rate: " + (INTEREST_RATE * 100) + "%");
    }
    
    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
