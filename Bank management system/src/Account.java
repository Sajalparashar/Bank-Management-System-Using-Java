import java.util.ArrayList;
import java.util.List;
//create account class
public class Account {
    private String accountHolderName;
    private String password;
    private double balance;
    private String accountNumber;
    private List<String> transactionHistory;

    public Account(String accountHolderName, String password, double balance, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.password = password;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + balance);
    }
//accounnt holder craetiioon
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            transactionHistory.add("Failed Withdrawal Attempt: " + amount + ", Insufficient Balance");
        }
    }
}
