# Online Banking System - Presentation Outline

## Slide 1: Title Slide
**Online Banking System**
A Java Object-Oriented Programming Project

---

## Slide 2: Table of Contents
1. Project Overview
2. System Architecture
3. Core Classes & Components
4. Object-Oriented Concepts
5. Key Features & Functionality
6. Technical Implementation
7. Code Examples
8. Learning Outcomes

---

## Slide 3: Project Overview
- **Project Name**: Online Banking System
- **Programming Language**: Java
- **Architecture**: Object-Oriented Programming (OOP)
- **Purpose**: Demonstrate core OOP principles through a practical banking application
- **Target Audience**: Java learners, OOP students

---

## Slide 4: System Architecture

```
┌─────────────────────────────────────┐
│           Bank System               │
├─────────────────────────────────────┤
│  ┌─────────┐      ┌─────────────┐  │
│  │  User   │ ◄──► │    Bank     │  │
│  └────┬────┘      └──────┬──────┘  │
│       │                  │         │
│       │ manages          │         │
│       ▼                  ▼         │
│  ┌─────────────────────────────┐  │
│  │     List<Account>           │  │
│  └─────────────────────────────┘  │
└─────────────────────────────────────┘
         ▲
         │
         │ inheritance
         │
    ┌────┴────┬──────────────┬─────────────┐
    │         │              │             │
┌───┴───┐ ┌───┴───┐   ┌─────┴─────┐ ┌──────┴─────┐
│Saving ││Checking│   │Certificate │ │  Account   │
│Account││Account │   │of Deposit  │ │ (Abstract) │
└───────┘└────────┘   └────────────┘ └────────────┘
```

---

## Slide 5: Core Components Overview

### Main Classes:
1. **Account** (Abstract Base Class)
2. **SavingsAccount** - Interest-bearing accounts
3. **CheckingAccount** - Accounts with overdraft protection
4. **CertificateOfDeposit** - Time-deposit accounts with maturity
5. **User** - Bank customer with multiple accounts
6. **Bank** - Central management system
7. **TestBank** - Demonstration program

---

## Slide 6: Class Hierarchy (UML)

```
                 ┌──────────────────┐
                 │   <<Abstract>>   │
                 │     Account      │
                 ├──────────────────┤
                 │ # accountNumber  │
                 │ # balance        │
                 ├──────────────────┤
                 │ + deposit()      │
                 │ + withdraw()     │
                 │ + displayAccount()│
                 │ + getAccountType()│
                 └────────┬─────────┘
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
  ┌─────┴─────┐   ┌───────┴───────┐   ┌─────┴──────┐
  │  Savings  │   │   Checking    │   │Certificate │
  │  Account  │   │   Account     │   │of Deposit  │
  ├───────────┤   ├───────────────┤   ├────────────┤
  │ - interest│   │ - overdraft   │   │ - maturity │
  │   Rate    │   │   Limit       │   │   Date     │
  ├───────────┤   ├───────────────┤   ├────────────┤
  │ + withdraw()│ │ + withdraw()  │   │ + isMatured()│
  └───────────┘   └───────────────┘   └────────────┘
```

---

## Slide 7: Object-Oriented Concepts Demonstrated

### 1. **Encapsulation**
- Private data fields with getters/setters
- Protected members for inheritance access
- Public methods for interface

### 2. **Inheritance**
- Abstract base class `Account`
- Specialized subclasses: Savings, Checking, CertificateOfDeposit
- Code reuse and hierarchical design

### 3. **Polymorphism**
- Method overriding in subclasses
- Different behaviors for same operation
- Runtime type binding

### 4. **Abstraction**
- Abstract methods in base class
- Interface separation from implementation

---

## Slide 8: Key Features by Account Type

| Account Type | Key Features | Interest | Overdraft | Maturity |
|--------------|--------------|----------|-----------|----------|
| **Savings** | Interest-earning | 2% annually | No | N/A |
| **Checking** | Daily transactions | None | Yes ($100) | N/A |
| **Certificate** | Fixed-term deposit | Varies | No | Yes |

---

## Slide 9: Technical Implementation

### Data Types & Structures:
- **double**: Account balances, monetary values
- **String**: Account numbers, user names
- **LocalDate**: Maturity dates
- **ArrayList<Account>**: Collection of user accounts
- **static final**: Constants (interest rates, limits)

### Key Java Features:
- Exception handling (`IllegalArgumentException`)
- Enhanced for-loops
- Static members (user counter)
- `@Override` annotation
- Java Collections Framework

---

## Slide 10: User Class - Key Code Snippet

```java
public class User {
    private static int userCounter = 0;
    private int userId;
    private String name;
    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String name) {
        this.userId = ++userCounter;
        this.name = name;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void displayAccounts() {
        System.out.println("\n=== " + name + "'s Accounts ===");
        for (Account account : accounts) {
            account.displayAccount();
        }
    }
}
```

---

## Slide 11: Account Class - Abstraction & Inheritance

```java
public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public abstract void withdraw(double amount);
    public abstract void displayAccount();
    public abstract String getAccountType();
}
```

---

## Slide 12: Polymorphism in Action

```java
// TestBank.java - Demonstration
public class TestBank {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Register users and create different account types
        User user1 = bank.registerUser("John Doe");
        bank.openSavingsAccount(user1, 1000.0);
        bank.openCheckingAccount(user1, 500.0);
        bank.openCertificateOfDeposit(user1, 2000.0, 
                                      LocalDate.of(2024, 12, 31));

        // Polymorphic behavior
        user1.displayAccounts();  // Calls different displayAccount()
                                  // for each account type
    }
}
```

---

## Slide 13: SavingsAccount - Interest Feature

```java
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.02;  // 2%

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    @Override
    public void displayAccount() {
        System.out.println("Type: Savings Account");
        System.out.println("Interest Rate: " + (INTEREST_RATE * 100) + "%");
        System.out.println("Balance: $" + balance);
    }
}
```

---

## Slide 14: CheckingAccount - Overdraft Protection

```java
public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 100.0;

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance + OVERDRAFT_LIMIT) {
            balance -= amount;
        }
    }

    @Override
    public void displayAccount() {
        System.out.println("Type: Checking Account");
        System.out.println("Overdraft Limit: $" + OVERDRAFT_LIMIT);
        System.out.println("Balance: $" + balance);
    }
}
```

---

## Slide 15: CertificateOfDeposit - Maturity Check

```java
public class CertificateOfDeposit extends Account {
    private LocalDate maturityDate;

    public CertificateOfDeposit(String accountNumber, double balance,
                                LocalDate maturityDate) {
        super(accountNumber, balance);
        this.maturityDate = maturityDate;
    }

    public boolean isMatured() {
        return LocalDate.now().isAfter(maturityDate) ||
               LocalDate.now().equals(maturityDate);
    }

    @Override
    public void withdraw(double amount) {
        if (!isMatured()) {
            throw new IllegalArgumentException(
                "Certificate of Deposit has not matured yet!");
        }
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }
}
```

---

## Slide 16: System Workflow

```
┌──────────┐
│ Start    │
└────┬─────┘
     │
     ▼
┌─────────────┐      ┌─────────────────┐
│ Bank System │ ◄──► │     User        │
└──────┬──────┘      └─────────────────┘
       │
       │ 1. Register User
       │ 2. Create Account
       │ 3. Deposit/Withdraw
       │ 4. Display Info
       ▼
┌─────────────────────────────────┐
│   Account Operations Flow       │
├─────────────────────────────────┤
│  • Deposit: balance += amount   │
│  • Withdraw: Check account type │
│    - Savings: balance >= amount  │
│    - Checking: balance + limit  │
│    - CD: Must be matured        │
└─────────────────────────────────┘
```

---

## Slide 17: Learning Outcomes

By studying this project, you will understand:

1. **OOP Fundamentals**
   - Classes, objects, constructors
   - Access modifiers (private, protected, public)

2. **Inheritance**
   - Extending classes
   - Code reuse patterns

3. **Polymorphism**
   - Method overriding
   - Dynamic binding

4. **Abstraction**
   - Abstract classes and methods
   - Interface design

5. **Advanced Java**
   - Collections (ArrayList)
   - Exception handling
   - Static members
   - Date/time API

---

## Slide 18: Project Benefits

### For Students:
- Real-world application example
- Hands-on OOP practice
- Clear code structure
- Well-documented components

### For Educators:
- Progressive difficulty
- Multiple teaching points
- Demonstrates best practices
- Extendable architecture

---

## Slide 19: Extension Ideas

Potential enhancements for learning:

1. **Add Transaction History**
   - Track all deposits/withdrawals
   - Generate statements

2. **Implement Interest Calculation**
   - Calculate monthly/quarterly interest
   - Apply to savings accounts

3. **Add Security Features**
   - PIN authentication
   - Transaction limits

4. **Bank Database Integration**
   - JDBC for persistent storage
   - SQL data management

5. **GUI Application**
   - Swing or JavaFX interface
   - Interactive banking operations

---

## Slide 20: Conclusion

### Summary:
- The Online Banking System is an excellent **OOP learning project**
- Demonstrates all core object-oriented principles
- Provides practical, real-world context
- Clean, maintainable code structure

### Next Steps:
- Experiment with the code
- Try the extension ideas
- Build your own variations
- Apply concepts to new projects

---

## Slide 21: Q&A

**Questions?**

---

## Slide 22: Thank You

**Online Banking System**
*A Practical Java OOP Learning Project*

Email: your.email@example.com
Project Repository: github.com/your-repo/online-banking
