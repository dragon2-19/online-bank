import java.time.LocalDate;

/**
 * 定期存单类（CD - Certificate of Deposit）
 */
public class CertificateOfDeposit extends Account {
    private LocalDate maturityDate;
    private double interestRate;
    
    public CertificateOfDeposit(String accountNumber, double initialBalance, 
                                User owner, int termMonths, double interestRate) {
        super(accountNumber, initialBalance, owner);
        this.maturityDate = LocalDate.now().plusMonths(termMonths);
        this.interestRate = interestRate;
    }
    
    /**
     * 获取到期日期
     */
    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    
    /**
     * 检查是否已到期
     */
    public boolean isMatured() {
        return LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate);
    }
    
    /**
     * 取款 - 到期前不可取款
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("取款金额不能为负数");
        }
        
        if (!isMatured()) {
            System.err.println("错误：定期存单尚未到期，到期日期为 " + maturityDate);
            return false;
        }
        
        if (amount > balance) {
            System.err.println("错误：账户余额不足");
            return false;
        }
        
        this.balance -= amount;
        return true;
    }
    
    @Override
    public void displayAccount() {
        System.out.println("账户类型: 定期存单 (CD)");
        System.out.println("账户号码: " + accountNumber);
        System.out.println("账户余额: " + balance);
        System.out.println("到期日期: " + maturityDate);
        System.out.println("年利率: " + (interestRate * 100) + "%");
        System.out.println("是否到期: " + (isMatured() ? "是" : "否"));
    }
    
    @Override
    public String getAccountType() {
        return "定期存单";
    }
}
