import java.util.ArrayList;
import java.util.List;

/**
 * User class
 */
public class User {
    private String userId;
    private String name;
    private List<Account> accounts;
    
    private static int userCounter = 0;
    
    public User(String name) {
        this.userId = "U" + (++userCounter);
        this.name = name;
        this.accounts = new ArrayList<>();
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Account> getAccounts() {
        return accounts;
    }
    
    /**
     * Open account
     */
    public void openAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
    }
    
    /**
     * Close account
     */
    public boolean closeAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() > 0) {
                System.out.println("Warning: Account " + accountNumber + " still has balance: " + account.getBalance());
            }
            return accounts.remove(account);
        }
        System.err.println("Error: Account not found: " + accountNumber);
        return false;
    }
    
    /**
     * Find account
     */
    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    /**
     * Display accounts report
     */
    public void displayAccountsReport() {
        System.out.println("\n=== User Account Report ===");
        System.out.println("User ID: " + userId);
        System.out.println("User Name: " + name);
        System.out.println("Number of Accounts: " + accounts.size());
        System.out.println("-------------------");
        
        for (Account account : accounts) {
            account.displayAccount();
            System.out.println();
        }
        
        double totalBalance = calculateTotalBalance();
        System.out.println("Total Balance: " + totalBalance);
        System.out.println("===================\n");
    }
    
    /**
     * Calculate total balance
     */
    private double calculateTotalBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "User [ID: " + userId + ", Name: " + name + "]";
    }
}
