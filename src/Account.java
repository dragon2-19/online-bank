/**
 * Abstract base class - Bank Account
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected User owner;
    
    public Account(String accountNumber, double initialBalance, User owner) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.owner = owner;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public User getOwner() {
        return owner;
    }
    
    /**
     * Deposit money
     * @param amount deposit amount
     * @throws IllegalArgumentException if depositing a negative value
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative amount");
        }
        this.balance += amount;
    }
    
    /**
     * Withdraw money
     * @param amount withdrawal amount
     * @return whether withdrawal was successful
     */
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw negative amount");
        }
        if (amount > balance) {
            System.err.println("Error: Insufficient balance");
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
    /**
     * Display account information (abstract method, subclasses must implement)
     */
    public abstract void displayAccount();
    
    /**
     * Get account type
     */
    public abstract String getAccountType();
}
