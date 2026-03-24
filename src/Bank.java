import java.util.ArrayList;
import java.util.List;

/**
 * Bank class
 */
public class Bank {
    private String bankName;
    private List<User> users;
    private static int accountCounter = 0;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.users = new ArrayList<>();
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    /**
     * Register user
     */
    public User registerUser(String userName) {
        User user = new User(userName);
        users.add(user);
        return user;
    }
    
    /**
     * Remove user
     */
    public boolean removeUser(String userId) {
        User user = findUser(userId);
        if (user != null) {
            if (!user.getAccounts().isEmpty()) {
                System.out.println("Warning: User still has accounts, cannot remove");
                return false;
            }
            return users.remove(user);
        }
        System.err.println("Error: User not found: " + userId);
        return false;
    }
    
    /**
     * Find user
     */
    public User findUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Open account
     */
    public Account openAccount(User user, String accountType, double initialBalance) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        String accountNumber = "ACC" + (++accountCounter);
        Account account;
        
        switch (accountType.toLowerCase()) {
            case "savings":
                account = new SavingsAccount(accountNumber, initialBalance, user);
                break;
            case "checking":
                account = new CheckingAccount(accountNumber, initialBalance, user);
                break;
            case "cd":
                throw new IllegalArgumentException("CD account requires term and interest rate");
            default:
                throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
        
        user.openAccount(account);
        return account;
    }
    
    /**
     * Open CD account (requires additional parameters)
     */
    public Account openCDAccount(User user, double initialBalance, int termMonths, double interestRate) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        String accountNumber = "ACC" + (++accountCounter);
        CertificateOfDeposit cd = new CertificateOfDeposit(
            accountNumber, initialBalance, user, termMonths, interestRate
        );
        user.openAccount(cd);
        return cd;
    }
    
    /**
     * Close account
     */
    public boolean closeAccount(User user, String accountNumber) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return user.closeAccount(accountNumber);
    }
    
    /**
     * Deposit
     */
    public boolean deposit(User user, String accountNumber, double amount) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Account account = user.findAccount(accountNumber);
        if (account != null) {
            try {
                account.deposit(amount);
                return true;
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
                return false;
            }
        }
        System.err.println("Error: Account not found: " + accountNumber);
        return false;
    }
    
    /**
     * Withdraw
     */
    public boolean withdraw(User user, String accountNumber, double amount) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Account account = user.findAccount(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        System.err.println("Error: Account not found: " + accountNumber);
        return false;
    }
    
    /**
     * Display all users report
     */
    public void displayAllUsersReport() {
        System.out.println("\n========================================");
        System.out.println("           " + bankName + " - User Report");
        System.out.println("========================================");
        System.out.println("Total Users: " + users.size());
        System.out.println("----------------------------------------");
        
        for (User user : users) {
            user.displayAccountsReport();
        }
        
        System.out.println("========================================\n");
    }
    
    /**
     * Get total assets
     */
    public double getTotalAssets() {
        double total = 0;
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                total += account.getBalance();
            }
        }
        return total;
    }
}
