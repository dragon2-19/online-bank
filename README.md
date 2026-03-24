# Online Banking System

## Project Overview

This is a Java-based online banking system backend that demonstrates object-oriented programming concepts including encapsulation, inheritance, and polymorphism.

## Class Structure

```
Bank (Bank)
├── User (User)
│   └── Account (Account - Abstract Class)
│       ├── SavingsAccount (Savings Account)
│       ├── CheckingAccount (Checking Account)
│       └── CertificateOfDeposit (Certificate of Deposit)
```

## Core Features

### 1. Bank Class
- Manages all users
- User registration/removal
- Account opening/closing
- Deposit/withdrawal operations
- Generate bank reports

### 2. User Class
- Owns multiple accounts
- Manages own accounts
- Display account reports
- Auto-generates unique user IDs

### 3. Account Class (Abstract Base Class)
- Account number
- Balance
- Account owner
- Abstract methods: displayAccount(), getAccountType()
- Core methods: deposit(), withdraw()

### 4. SavingsAccount
- Annual interest rate: 2%
- Can apply interest

### 5. CheckingAccount
- Overdraft limit: 100
- Supports withdrawals exceeding balance (within limit)

### 6. CertificateOfDeposit
- Maturity date
- Configurable interest rate
- **Business Rule**: Cannot withdraw before maturity

## Design Decisions and Assumptions

### 1. Account Number Generation
- Uses static counter `accountCounter` to auto-generate unique account numbers
- Format: ACC1, ACC2, ACC3...

### 2. User ID Generation
- Uses static counter `userCounter` to auto-generate unique user IDs
- Format: U1, U2, U3...

### 3. Exception Handling
- Depositing negative values throws `IllegalArgumentException`
- Withdrawals exceeding balance print error message to terminal
- Withdrawals from CD before maturity print error message to terminal

### 4. Business Rule Implementation
- CD maturity check: `isMatured()` method compares current date with maturity date
- Balance checking: Validated in `withdraw()` method

### 5. Polymorphism Application
- `Account` as abstract base class defines common interface
- Different account types implement their specific behaviors
- Type checking via `instanceof` to call specific methods

### 6. Encapsulation
- All fields use `private` or `protected` modifiers
- Access through getter/setter methods
- Business logic controlled through public methods

### 7. Static Members
- `userCounter` and `accountCounter` use static for uniqueness
- `INTEREST_RATE` and `OVERDRAFT_LIMIT` use static as constants

### 8. Test Class
- `TestBank` contains main method
- Demonstrates all core functionality
- No more than 6 instances of each type (3 users, max 3 accounts each)

## Compilation and Execution

```bash
# Compile all Java files
javac src/*.java

# Run tests
java -cp src TestBank
```

## Feature Demonstrations

The TestBank class demonstrates the following features:
1. Create bank
2. Register users
3. Open different account types (savings, checking, CD)
4. Deposit operations
5. Normal withdrawals
6. Insufficient balance withdrawal attempts
7. Negative value deposit exception handling
8. CD pre-maturity withdrawal restrictions
9. User account reports
10. Bank all users report
11. Interest application (polymorphism demo)
12. Bank total assets calculation

## Technical Highlights

1. **Encapsulation**: All fields use appropriate access modifiers
2. **Inheritance**: Clear account class hierarchy
3. **Polymorphism**: Application of abstract classes and interfaces
4. **Abstract Classes**: Account defines common interface
5. **Static Members**: Counters and constants
6. **Exception Handling**: Reasonable exception throwing and error messages
7. **Business Rules**: CD maturity restrictions implementation
8. **Code Readability**: Clear naming and comments

## Extensibility

The system is designed for easy extension:
- Can add new account types
- Can add transaction history
- Can implement user authentication
- Can add more business rules

