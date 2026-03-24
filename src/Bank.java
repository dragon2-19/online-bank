import java.util.ArrayList;
import java.util.List;

/**
 * 银行类
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
     * 用户注册
     */
    public User registerUser(String userName) {
        User user = new User(userName);
        users.add(user);
        return user;
    }
    
    /**
     * 注销用户
     */
    public boolean removeUser(String userId) {
        User user = findUser(userId);
        if (user != null) {
            if (!user.getAccounts().isEmpty()) {
                System.out.println("警告：用户仍有账户，无法注销");
                return false;
            }
            return users.remove(user);
        }
        System.err.println("错误：未找到用户 " + userId);
        return false;
    }
    
    /**
     * 查找用户
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
     * 开户
     */
    public Account openAccount(User user, String accountType, double initialBalance) {
        if (user == null) {
            throw new IllegalArgumentException("用户不能为空");
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
                throw new IllegalArgumentException("CD 账户需要指定期限和利率");
            default:
                throw new IllegalArgumentException("未知账户类型: " + accountType);
        }
        
        user.openAccount(account);
        return account;
    }
    
    /**
     * 开户（CD 需要额外参数）
     */
    public Account openCDAccount(User user, double initialBalance, int termMonths, double interestRate) {
        if (user == null) {
            throw new IllegalArgumentException("用户不能为空");
        }
        
        String accountNumber = "ACC" + (++accountCounter);
        CertificateOfDeposit cd = new CertificateOfDeposit(
            accountNumber, initialBalance, user, termMonths, interestRate
        );
        user.openAccount(cd);
        return cd;
    }
    
    /**
     * 销户
     */
    public boolean closeAccount(User user, String accountNumber) {
        if (user == null) {
            throw new IllegalArgumentException("用户不能为空");
        }
        return user.closeAccount(accountNumber);
    }
    
    /**
     * 存款
     */
    public boolean deposit(User user, String accountNumber, double amount) {
        if (user == null) {
            throw new IllegalArgumentException("用户不能为空");
        }
        Account account = user.findAccount(accountNumber);
        if (account != null) {
            try {
                account.deposit(amount);
                return true;
            } catch (IllegalArgumentException e) {
                System.err.println("错误: " + e.getMessage());
                return false;
            }
        }
        System.err.println("错误：未找到账户 " + accountNumber);
        return false;
    }
    
    /**
     * 取款
     */
    public boolean withdraw(User user, String accountNumber, double amount) {
        if (user == null) {
            throw new IllegalArgumentException("用户不能为空");
        }
        Account account = user.findAccount(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        System.err.println("错误：未找到账户 " + accountNumber);
        return false;
    }
    
    /**
     * 显示所有用户报告
     */
    public void displayAllUsersReport() {
        System.out.println("\n========================================");
        System.out.println("           " + bankName + " - 用户报告");
        System.out.println("========================================");
        System.out.println("用户总数: " + users.size());
        System.out.println("----------------------------------------");
        
        for (User user : users) {
            user.displayAccountsReport();
        }
        
        System.out.println("========================================\n");
    }
    
    /**
     * 获取银行总资产
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
