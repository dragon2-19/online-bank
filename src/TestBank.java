/**
 * Test class - Demonstrate online banking system functionality
 */
public class TestBank {
    public static void main(String[] args) {
        System.out.println("=== Online Banking System Demo ===\n");
        
        // Create bank
        Bank bank = new Bank("Online Bank");
        System.out.println("Bank created successfully: " + bank.getBankName());
        
        // Register users
        System.out.println("\n--- Registering Users ---");
        User user1 = bank.registerUser("Alice");
        User user2 = bank.registerUser("Bob");
        User user3 = bank.registerUser("Charlie");
        System.out.println("User registered: " + user1.getName() + " (ID: " + user1.getUserId() + ")");
        System.out.println("User registered: " + user2.getName() + " (ID: " + user2.getUserId() + ")");
        System.out.println("User registered: " + user3.getName() + " (ID: " + user3.getUserId() + ")");
        
        // Open accounts for user1
        System.out.println("\n--- Opening Accounts for " + user1.getName() + " ---");
        Account savings1 = bank.openAccount(user1, "savings", 10000.0);
        System.out.println("Savings account opened: " + savings1.getAccountNumber());
        
        Account checking1 = bank.openAccount(user1, "checking", 5000.0);
        System.out.println("Checking account opened: " + checking1.getAccountNumber());
        
        Account cd1 = bank.openCDAccount(user1, 20000.0, 12, 0.035);
        System.out.println("CD account opened: " + cd1.getAccountNumber() + " (12 months, 3.5% interest)");
        
        // Open accounts for user2
        System.out.println("\n--- Opening Accounts for " + user2.getName() + " ---");
        Account savings2 = bank.openAccount(user2, "savings", 5000.0);
        System.out.println("Savings account opened: " + savings2.getAccountNumber());
        
        Account checking2 = bank.openAccount(user2, "checking", 3000.0);
        System.out.println("Checking account opened: " + checking2.getAccountNumber());
        
        // Open accounts for user3
        System.out.println("\n--- Opening Accounts for " + user3.getName() + " ---");
        Account savings3 = bank.openAccount(user3, "savings", 15000.0);
        System.out.println("Savings account opened: " + savings3.getAccountNumber());
        
        // Deposit demonstration
        System.out.println("\n--- Deposit Operations ---");
        System.out.println("User " + user1.getName() + " deposits 2000 into savings account");
        bank.deposit(user1, savings1.getAccountNumber(), 2000.0);
        System.out.println("Account balance: " + savings1.getBalance());
        
        // Withdrawal demonstration
        System.out.println("\n--- Withdrawal Operations ---");
        System.out.println("User " + user1.getName() + " withdraws 1500 from checking account");
        boolean success = bank.withdraw(user1, checking1.getAccountNumber(), 1500.0);
        if (success) {
            System.out.println("Withdrawal successful. Balance: " + checking1.getBalance());
        }
        
        // Insufficient balance demonstration
        System.out.println("\n--- Insufficient Balance Test ---");
        System.out.println("User " + user1.getName() + " attempts to withdraw 5000 from checking account");
        success = bank.withdraw(user1, checking1.getAccountNumber(), 5000.0);
        if (!success) {
            System.out.println("Withdrawal failed. Balance: " + checking1.getBalance());
        }
        
        // Negative deposit exception demonstration
        System.out.println("\n--- Negative Deposit Test ---");
        System.out.println("User " + user1.getName() + " attempts to deposit -100");
        success = bank.deposit(user1, savings1.getAccountNumber(), -100.0);
        if (!success) {
            System.out.println("Deposit failed. Balance: " + savings1.getBalance());
        }
        
        // CD withdrawal before maturity demonstration
        System.out.println("\n--- CD Withdrawal Before Maturity Test ---");
        System.out.println("User " + user1.getName() + " attempts to withdraw 5000 from CD account");
        success = bank.withdraw(user1, cd1.getAccountNumber(), 5000.0);
        if (!success) {
            System.out.println("Withdrawal failed. CD balance: " + cd1.getBalance());
        }
        System.out.println("CD maturity date: " + ((CertificateOfDeposit) cd1).getMaturityDate());
        System.out.println("CD is matured: " + ((CertificateOfDeposit) cd1).isMatured());
        
        // User displays account reports
        System.out.println("\n--- User Account Reports ---");
        user1.displayAccountsReport();
        user2.displayAccountsReport();
        
        // Bank displays all users report
        System.out.println("\n--- Bank All Users Report ---");
        bank.displayAllUsersReport();
        
        // Demonstrate polymorphism - apply interest to all accounts
        System.out.println("\n--- Applying Interest (Polymorphism Demo) ---");
        for (User user : bank.getUsers()) {
            for (Account account : user.getAccounts()) {
                System.out.print("Account " + account.getAccountNumber() + " (" + account.getAccountType() + "): ");
                if (account instanceof SavingsAccount) {
                    ((SavingsAccount) account).applyInterest();
                    System.out.println("New balance: " + account.getBalance());
                } else {
                    System.out.println("Interest not applicable");
                }
            }
        }
        
        // Display bank total assets
        System.out.println("\n--- Bank Total Assets ---");
        System.out.println("Total bank assets: " + bank.getTotalAssets());
        
        System.out.println("\n=== Demo Complete ===");
    }
}
