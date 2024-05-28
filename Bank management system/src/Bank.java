import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public String createAccount(String accountHolderName, String password, double initialBalance) {
        String accountNumber = "ACC" + (accounts.size() + 1);
        Account newAccount = new Account(accountHolderName, password, initialBalance, accountNumber);
        accounts.put(accountNumber, newAccount);
        return accountNumber;
    }

    public boolean authenticate(String accountNumber, String password) {
        Account account = accounts.get(accountNumber);
        return account != null && account.getPassword().equals(password);
    }
//deposit of money


    public void depositToAccount(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
        }
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        }
    }
//balance check


    public double checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0;
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public String displayAccountDetails(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            return "Account not found.";
        }
        return "Account Holder: " + account.getAccountHolderName() + "\n" +
                "Account Number: " + account.getAccountNumber() + "\n" +
                "Balance: " + account.getBalance() + "\n" +
                "Transaction History: " + account.getTransactionHistory();
    }
}
