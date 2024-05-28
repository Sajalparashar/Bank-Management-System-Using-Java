import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUIBYSP extends JFrame {
    private Bank bank;
    private JTextField accountField1, amountField2, numberField3;
    private JPasswordField passField;
    private JTextArea displayArea;

    public BankGUIBYSP() {
        bank = new Bank();
        setTitle("SP Bank");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 102, 204));
        JLabel titleLabel = new JLabel("SP Bank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        accountField1 = new JTextField();
        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(accountField1);
        passField = new JPasswordField();
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passField);
        amountField2 = new JTextField();
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField2);
        numberField3 = new JTextField();
        inputPanel.add(new JLabel("Account Number:"));
        inputPanel.add(numberField3);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

// Create icons
        Icon createIcon = new ImageIcon("icons/create.png");
        Icon depositIcon = new ImageIcon("icons/deposit.png");
        Icon withdrawIcon = new ImageIcon("icons/withdraw.png");
        Icon checkBalanceIcon = new ImageIcon("icons/check_balance.png");
        Icon historyIcon = new ImageIcon("icons/transaction_history.png");

        JButton createButton = new JButton("Create Account", createIcon);
        JButton depositButton = new JButton("Deposit", depositIcon);
        JButton withdrawButton = new JButton("Withdraw", withdrawIcon);
        JButton checkButton = new JButton("Check Balance", checkBalanceIcon);
        JButton historyButton = new JButton("Transaction History", historyIcon);

        buttonsPanel.add(createButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(checkButton);
        buttonsPanel.add(historyButton);

;

        // Output Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        // Adding components to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(buttonsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Action Listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositAmount();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawAmount();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });
    }

    private void createAccount() {
        String name = accountField1.getText();
        String pass = new String(passField.getPassword());
        double initialAmt = Double.parseDouble(amountField2.getText());
        String accountNum = bank.createAccount(name, pass, initialAmt);
        displayArea.setText("Account created successfully.\nAccount Number: " + accountNum + "\n");
    }

    private boolean authenticate(String accNum, String pass) {
        if (bank.authenticate(accNum, pass)) {
            return true;
        } else {
            displayArea.setText("Authentication failed. Incorrect credentials.\n");
            return false;
        }
    }

    private void depositAmount() {
        String accNum = numberField3.getText();
        String pass = new String(passField.getPassword());
        if (authenticate(accNum, pass)) {
            double amt = Double.parseDouble(amountField2.getText());
            bank.depositToAccount(accNum, amt);
            displayArea.setText("Deposited: " + amt + "\n");
        }
    }

    private void withdrawAmount() {
        String accNum = numberField3.getText();
        String pass = new String(passField.getPassword());
        if (authenticate(accNum, pass)) {
            double amt = Double.parseDouble(amountField2.getText());
            bank.withdrawFromAccount(accNum, amt);
            displayArea.setText("Withdrawn: " + amt + "\n");
        }
    }

    private void checkBalance() {
        String accNum = numberField3.getText();
        String pass = new String(passField.getPassword());
        if (authenticate(accNum, pass)) {
            double bal = bank.checkBalance(accNum);
            displayArea.setText("Current balance: " + bal + "\n");
        }
    }

    private void showTransactionHistory() {
        String accNum = numberField3.getText();
        String pass = new String(passField.getPassword());
        if (authenticate(accNum, pass)) {
            Account account = bank.findAccount(accNum);
            displayArea.setText("Transaction History for Account: " + accNum + "\n" + account.getTransactionHistory().toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankGUIBYSP().setVisible(true);
            }
        });
    }
}
