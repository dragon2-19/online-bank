/**
 * 储蓄账户类
 */
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02; // 2% 年利率
    
    public SavingsAccount(String accountNumber, double initialBalance, User owner) {
        super(accountNumber, initialBalance, owner);
    }
    
    /**
     * 应用利息
     */
    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("应用利息: " + interest);
    }
    
    @Override
    public void displayAccount() {
        System.out.println("账户类型: 储蓄账户");
        System.out.println("账户号码: " + accountNumber);
        System.out.println("账户余额: " + balance);
        System.out.println("年利率: " + (INTEREST_RATE * 100) + "%");
    }
    
    @Override
    public String getAccountType() {
        return "储蓄账户";
    }
}
