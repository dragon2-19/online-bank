/**
 * 抽象基类 - 银行账户
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
     * 存款
     * @param amount 存款金额
     * @throws IllegalArgumentException 如果存入负值
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("存入金额不能为负数");
        }
        this.balance += amount;
    }
    
    /**
     * 取款
     * @param amount 取款金额
     * @return 是否取款成功
     */
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("取款金额不能为负数");
        }
        if (amount > balance) {
            System.err.println("错误：账户余额不足");
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
    /**
     * 显示账户信息（抽象方法，子类需实现）
     */
    public abstract void displayAccount();
    
    /**
     * 获取账户类型
     */
    public abstract String getAccountType();
}
