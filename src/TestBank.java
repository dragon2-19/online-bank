/**
 * 测试类 - 演示在线银行系统的功能
 */
public class TestBank {
    public static void main(String[] args) {
        System.out.println("=== 在线银行系统演示 ===\n");
        
        // 创建银行
        Bank bank = new Bank("在线银行");
        System.out.println("成功创建银行: " + bank.getBankName());
        
        // 注册用户
        System.out.println("\n--- 注册用户 ---");
        User user1 = bank.registerUser("张三");
        User user2 = bank.registerUser("李四");
        User user3 = bank.registerUser("王五");
        System.out.println("已注册用户: " + user1.getName() + " (ID: " + user1.getUserId() + ")");
        System.out.println("已注册用户: " + user2.getName() + " (ID: " + user2.getUserId() + ")");
        System.out.println("已注册用户: " + user3.getName() + " (ID: " + user3.getUserId() + ")");
        
        // 为用户1开设账户
        System.out.println("\n--- 为用户 " + user1.getName() + " 开设账户 ---");
        Account savings1 = bank.openAccount(user1, "savings", 10000.0);
        System.out.println("已开立储蓄账户: " + savings1.getAccountNumber());
        
        Account checking1 = bank.openAccount(user1, "checking", 5000.0);
        System.out.println("已开立支票账户: " + checking1.getAccountNumber());
        
        Account cd1 = bank.openCDAccount(user1, 20000.0, 12, 0.035);
        System.out.println("已开立定期存单: " + cd1.getAccountNumber() + " (12个月, 3.5%利率)");
        
        // 为用户2开设账户
        System.out.println("\n--- 为用户 " + user2.getName() + " 开设账户 ---");
        Account savings2 = bank.openAccount(user2, "savings", 5000.0);
        System.out.println("已开立储蓄账户: " + savings2.getAccountNumber());
        
        Account checking2 = bank.openAccount(user2, "checking", 3000.0);
        System.out.println("已开立支票账户: " + checking2.getAccountNumber());
        
        // 为用户3开设账户
        System.out.println("\n--- 为用户 " + user3.getName() + " 开设账户 ---");
        Account savings3 = bank.openAccount(user3, "savings", 15000.0);
        System.out.println("已开立储蓄账户: " + savings3.getAccountNumber());
        
        // 演示存款功能
        System.out.println("\n--- 存款操作 ---");
        System.out.println("用户 " + user1.getName() + " 向储蓄账户存入 2000");
        bank.deposit(user1, savings1.getAccountNumber(), 2000.0);
        System.out.println("账户余额: " + savings1.getBalance());
        
        // 演示取款功能
        System.out.println("\n--- 取款操作 ---");
        System.out.println("用户 " + user1.getName() + " 从支票账户取款 1500");
        boolean success = bank.withdraw(user1, checking1.getAccountNumber(), 1500.0);
        if (success) {
            System.out.println("取款成功，余额: " + checking1.getBalance());
        }
        
        // 演示余额不足的情况
        System.out.println("\n--- 余额不足测试 ---");
        System.out.println("用户 " + user1.getName() + " 尝试从支票账户取款 5000");
        success = bank.withdraw(user1, checking1.getAccountNumber(), 5000.0);
        if (!success) {
            System.out.println("取款失败，余额: " + checking1.getBalance());
        }
        
        // 演示存款负值异常
        System.out.println("\n--- 负值存款测试 ---");
        System.out.println("用户 " + user1.getName() + " 尝试存入 -100");
        success = bank.deposit(user1, savings1.getAccountNumber(), -100.0);
        if (!success) {
            System.out.println("存款失败，余额: " + savings1.getBalance());
        }
        
        // 演示CD到期前不可取款
        System.out.println("\n--- 定期存单到期前取款测试 ---");
        System.out.println("用户 " + user1.getName() + " 尝试从定期存单取款 5000");
        success = bank.withdraw(user1, cd1.getAccountNumber(), 5000.0);
        if (!success) {
            System.out.println("取款失败，CD余额: " + cd1.getBalance());
        }
        System.out.println("CD到期日期: " + ((CertificateOfDeposit) cd1).getMaturityDate());
        System.out.println("CD是否到期: " + ((CertificateOfDeposit) cd1).isMatured());
        
        // 用户显示账户报告
        System.out.println("\n--- 用户账户报告 ---");
        user1.displayAccountsReport();
        user2.displayAccountsReport();
        
        // 银行显示所有用户报告
        System.out.println("\n--- 银行所有用户报告 ---");
        bank.displayAllUsersReport();
        
        // 演示多态 - 对所有账户应用利息
        System.out.println("\n--- 应用利息（多态演示） ---");
        for (User user : bank.getUsers()) {
            for (Account account : user.getAccounts()) {
                System.out.print("账户 " + account.getAccountNumber() + " (" + account.getAccountType() + "): ");
                if (account instanceof SavingsAccount) {
                    ((SavingsAccount) account).applyInterest();
                    System.out.println("新余额: " + account.getBalance());
                } else {
                    System.out.println("不适用利息");
                }
            }
        }
        
        // 显示银行总资产
        System.out.println("\n--- 银行总资产 ---");
        System.out.println("银行总资产: " + bank.getTotalAssets());
        
        System.out.println("\n=== 演示完成 ===");
    }
}
