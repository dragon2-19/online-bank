# Online Banking System Presentation

## Slide 1: Title
**Online Banking System**
A Java OOP Project

---

## Slide 2: Project Overview
- Language: Java
- Paradigm: Object-Oriented Programming
- Purpose: Demonstrate OOP concepts through banking application
- Key Concepts: Inheritance, Polymorphism, Encapsulation

---

## Slide 3: System Architecture

```
                    Bank
            (manages users)
                  │
        ┌─────────┼─────────┐
        │         │         │
      User1     User2     User3
        │         │         │
    ┌───┴───┐ ┌───┴───┐ ┌───┴───┐
    │Accounts│ │Accounts│ │Accounts│
    │  List  │ │  List  │ │  List  │
    └───┬───┘ └───┬───┘ └───┬───┘
        │         │         │
    ┌───┴─────────┴─────────┴───┐
    │     Account (Abstract)    │
    │  ─────────────────────── │
    │  • accountNumber          │
    │  • balance                │
    │  • owner (User)           │
    │  • deposit(), withdraw()  │
    └───┬─────┬─────┬──────────┘
        │     │     │
        │     │     └─ CertificateOfDeposit
        │     │
        │     └─── CheckingAccount
        │
        └─────── SavingsAccount
```

---

## Slide 4: Class Diagram

```
┌─────────────────┐
│     Bank        │
├─────────────────┤
│ - bankName      │
│ - users         │
├─────────────────┤
│ + registerUser()│
│ + openAccount() │
│ + deposit()     │
│ + withdraw()    │
└────────┬────────┘
         │ 1
         │ has
         │ *
┌────────▼────────┐
│     User        │
├─────────────────┤
│ - userId        │
│ - name          │
│ - accounts      │
├─────────────────┤
│ + openAccount() │
│ + findAccount() │
└────────┬────────┘
         │ 1
         │ has
         │ *
┌────────▼─────────────────┐
│  Account (Abstract)      │
├──────────────────────────┤
│ # accountNumber          │
│ # balance                │
│ # owner                  │
├──────────────────────────┤
│ + deposit()              │
│ + withdraw()             │
│ + displayAccount() [abst]│
│ + getAccountType() [abst]│
└────────┬─────────────────┘
         │
    ┌────┴────┬──────────┬
    │         │          │          
┌───▼────┐ ┌─▼──────┐ ┌─▼──────┐ 
│Savings ││Checking ││CD       │
│Account ││Account  ││Account  │
└────────┘└─────────┘└─────────┘
```

---

## Slide 5: Account Hierarchy

| Class | Type | Key Features |
|-------|------|--------------|
| **Account** | Abstract | Base class with common methods |
| **SavingsAccount** | Subclass | Interest-bearing account |
| **CheckingAccount** | Subclass | Transaction account |
| **CertificateOfDeposit** | Subclass | Time-deposit with maturity date |

---

## Slide 6: Key Classes

### 1. Bank
- Manages all users
- Handles user registration/removal
- Central point for account operations

### 2. User
- Has unique ID and name
- Owns multiple accounts
- Can open/close accounts

### 3. Account
- Abstract base class
- Contains balance and account number
- Defines common operations

---

## Slide 7: OOP Concepts Demonstrated

1. **Encapsulation**
    - Private fields with getters
    - Protected fields for inheritance

2. **Inheritance**
    - Account hierarchy
    - Code reuse in subclasses

3. **Polymorphism**
    - Method overriding
    - Same interface, different implementations

4. **Abstraction**
    - Abstract base class
    - Abstract methods

---

## Slide 8: Code Example - Account Class

```java
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected User owner;

    public Account(String accountNumber, double balance, User owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative amount");
        }
        this.balance += amount;
    }

    public abstract void displayAccount();
    public abstract String getAccountType();
}
```

---

## Slide 9: Code Example - User Class

```java
public class User {
    private String userId;
    private String name;
    private List<Account> accounts;

    public User(String name) {
        this.userId = "U" + (++userCounter);
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void openAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
```

---

## Slide 10: Code Example - Bank Class

```java
public class Bank {
    private String bankName;
    private List<User> users;

    public User registerUser(String userName) {
        User user = new User(userName);
        users.add(user);
        return user;
    }

    public Account openAccount(User user, String accountType, double balance) {
        String accountNumber = "ACC" + (++accountCounter);
        Account account;

        switch (accountType.toLowerCase()) {
            case "savings":
                account = new SavingsAccount(accountNumber, balance, user);
                break;
            case "checking":
                account = new CheckingAccount(accountNumber, balance, user);
                break;
            default:
                throw new IllegalArgumentException("Unknown account type");
        }

        user.openAccount(account);
        return account;
    }
}
```

---

## Slide 11: System Workflow

```
1. Create Bank
       │
       ▼
2. Register Users
       │
       ▼
3. Open Accounts (Savings/Checking/CD)
       │
       ▼
4. Perform Operations
   • Deposit
   • Withdraw
   • Close Account
       │
       ▼
5. View Reports
   • User account report
   • Bank total assets
```

---

## Slide 12: Key Features

- User registration with unique IDs
- Multiple account types per user
- Deposit and withdraw operations
- Account balance management
- Exception handling for invalid operations
- Account closure functionality
- Interest application for savings accounts
- Bank-wide reporting

---

## Slide 13: Learning Outcomes

✓ Understanding of Java classes and objects
✓ Inheritance and abstract classes
✓ Method overriding and polymorphism
✓ Collection framework (ArrayList)
✓ Exception handling
✓ Static members
✓ Access modifiers (public, private, protected)

---

## Slide 14: Conclusion

The Online Banking System demonstrates:
- Real-world OOP application
- Clean class hierarchy
- Proper encapsulation
- Effective use of inheritance and polymorphism

**Perfect for learning Java OOP!**

---

## Slide 15: Q&A

Questions?
