import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
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
     * 开户
     */
    public void openAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("账户不能为空");
        }
        accounts.add(account);
    }
    
    /**
     * 销户
     */
    public boolean closeAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() > 0) {
                System.out.println("警告：账户 " + accountNumber + " 仍有余额 " + account.getBalance());
            }
            return accounts.remove(account);
        }
        System.err.println("错误：未找到账户 " + accountNumber);
        return false;
    }
    
    /**
     * 查找账户
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
     * 显示账户报告
     */
    public void displayAccountsReport() {
        System.out.println("\n=== 用户账户报告 ===");
        System.out.println("用户ID: " + userId);
        System.out.println("用户名: " + name);
        System.out.println("账户数量: " + accounts.size());
        System.out.println("-------------------");
        
        for (Account account : accounts) {
            account.displayAccount();
            System.out.println();
        }
        
        double totalBalance = calculateTotalBalance();
        System.out.println("总余额: " + totalBalance);
        System.out.println("===================\n");
    }
    
    /**
     * 计算总余额
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
        return "用户 [ID: " + userId + ", 姓名: " + name + "]";
    }
}
