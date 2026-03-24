import java.time.LocalDate;

/**
 * Certificate of Deposit class (CD)
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
     * Get maturity date
     */
    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    
    /**
     * Check if matured
     */
    public boolean isMatured() {
        return LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate);
    }
    
    /**
     * Withdraw - cannot withdraw before maturity
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw negative amount");
        }
        
        if (!isMatured()) {
            System.err.println("Error: CD not matured yet. Maturity date: " + maturityDate);
            return false;
        }
        
        if (amount > balance) {
            System.err.println("Error: Insufficient balance");
            return false;
        }
        
        this.balance -= amount;
        return true;
    }
    
    @Override
    public void displayAccount() {
        System.out.println("Account Type: Certificate of Deposit (CD)");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Balance: " + balance);
        System.out.println("Maturity Date: " + maturityDate);
        System.out.println("Annual Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("Is Matured: " + (isMatured() ? "Yes" : "No"));
    }
    
    @Override
    public String getAccountType() {
        return "Certificate of Deposit";
    }
}
