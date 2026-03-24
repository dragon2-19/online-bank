/**
 * 支票账户类
 */
public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 100.0; // 透支限额
    
    public CheckingAccount(String accountNumber, double initialBalance, User owner) {
        super(accountNumber, initialBalance, owner);
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("取款金额不能为负数");
        }
        if (amount > balance + OVERDRAFT_LIMIT) {
            System.err.println("错误：超出账户余额和透支限额");
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
        System.out.println("账户类型: 支票账户");
        System.out.println("账户号码: " + accountNumber);
        System.out.println("账户余额: " + balance);
        System.out.println("透支限额: " + OVERDRAFT_LIMIT);
    }
    
    @Override
    public String getAccountType() {
        return "支票账户";
    }
}
