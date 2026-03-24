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

        // Demonstrate CD withdrawal before maturity
        System.out.println("\n--- CD Early Withdrawal Test ---");
        System.out.println("User " + user1.getName() + " attempts to withdraw 5000 from CD");
        success = bank.withdraw(user1, cd1.getAccountNumber(), 5000.0);
        if (!success) {
            System.out.println("Withdrawal failed, CD balance: " + cd1.getBalance());
        }
        System.out.println("CD Maturity Date: " + ((CertificateOfDeposit) cd1).getMaturityDate());
        System.out.println("CD Is Matured: " + ((CertificateOfDeposit) cd1).isMatured());

        // New: Account Closure Functionality Test
        System.out.println("\n--- Account Closure Test ---");

        // Test 1: Open a new account for user3 for closure testing
        System.out.println("Opening new savings account for " + user3.getName() + " for closure test");
        Account testAccount = bank.openAccount(user3, "savings", 0.0);
        System.out.println("Opened test account: " + testAccount.getAccountNumber() + " (Balance: 0)");

        // Test 2: Successful closure (zero balance)
        System.out.println("\n[SUCCESS TEST] Attempt to close account with zero balance");
        boolean closeSuccess = bank.closeAccount(user3, testAccount.getAccountNumber());
        if (closeSuccess) {
            System.out.println("✓ Account closed successfully! Account " + testAccount.getAccountNumber() + " has been closed");
        } else {
            System.out.println("✗ Account closure failed");
        }

        // Verify account list after closure
        System.out.println("User " + user3.getName() + " current account count: " + user3.getAccounts().size());

        // Test 3: Attempt to close non-existent account
        System.out.println("\n[FAILURE TEST] Attempt to close non-existent account");
        closeSuccess = bank.closeAccount(user3, "ACC999");
        if (!closeSuccess) {
            System.out.println("✗ Account closure failed (expected) - Account does not exist");
        }

        // Test 4: Attempt to close account with balance (warning message)
        System.out.println("\n[WARNING TEST] Attempt to close account with balance");
        System.out.println("User " + user2.getName() + "'s savings account balance: " + savings2.getBalance());
        closeSuccess = bank.closeAccount(user2, savings2.getAccountNumber());
        if (closeSuccess) {
            System.out.println("⚠ Account closed successfully, but system issued balance warning");
        } else {
            System.out.println("✗ Account closure failed");
        }

        // Check if user3 has any remaining accounts
        if (user3.getAccounts().isEmpty()) {
            System.out.println("User " + user3.getName() + " has no accounts, attempting user removal");
            boolean removeUserSuccess = bank.removeUser(user3.getUserId());
            if (removeUserSuccess) {
                System.out.println("✓ User removed successfully: " + user3.getUserId());
            } else {
                System.out.println("✗ User removal failed");
            }
        }

        // Test 5: Remove user after closing their last account
        System.out.println("\n[COMPREHENSIVE TEST] Account cleanup before user removal");
        System.out.println("User " + user3.getName() + " current account count: " + user3.getAccounts().size());

        // Test 6: Attempt to remove user who still has accounts (should fail)
        System.out.println("\n[FAILURE TEST] Attempt to remove user with active accounts");
        System.out.println("User " + user1.getName() + " current account count: " + user1.getAccounts().size());
        boolean removeUserSuccess = bank.removeUser(user1.getUserId());
        if (!removeUserSuccess) {
            System.out.println("✗ User removal failed (expected) - User still has accounts");
        }


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
