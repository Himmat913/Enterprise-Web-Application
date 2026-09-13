class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {
    private int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }
}

class Bank {
    private BankAccount[] accounts;

    public Bank(BankAccount[] accounts) {
        this.accounts = accounts;
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found: " + accountNumber);
    }

    public void processWithdrawal(int accountNumber, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
        BankAccount acc = findAccount(accountNumber);
        acc.withdraw(amount);
    }
}

public class Ex5_BankingApplication {
    public static void main(String[] args) {
        BankAccount[] accounts = {
            new BankAccount(101, 5000),
            new BankAccount(102, 2000)
        };
        Bank bank = new Bank(accounts);

        try {
            bank.processWithdrawal(101, 1500);
            System.out.println("Balance: " + bank.findAccount(101).getBalance());
        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            bank.processWithdrawal(102, 3000);
        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            bank.processWithdrawal(999, 100);
        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            accounts[0].deposit(-50);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }
}
