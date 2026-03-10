import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

abstract class BankAccount {

    protected int accountNumber;
    protected double balance;
    protected String password;

    public BankAccount(int accountNumber,double balance,String password){
        this.accountNumber=accountNumber;
        this.balance=balance;
        this.password=password;
    }

    public abstract void deposit(double amount);

    public abstract String withdraw(double amount,String password);

    public String checkBalance(String password){

        if(this.password.equals(password)){
            return "Balance: "+balance;
        }

        return "Wrong password";
    }
}

class SavingsAccount extends BankAccount{

    public SavingsAccount(int accountNumber,double balance,String password){
        super(accountNumber,balance,password);
    }

    public void deposit(double amount){
        balance+=amount;
    }

    public String withdraw(double amount,String password){

        if(!this.password.equals(password))
            return "Wrong password";

        if(amount>balance)
            return "Insufficient balance";

        balance-=amount;
        return "Withdraw successful";
    }
}

class CurrentAccount extends BankAccount{

    public CurrentAccount(int accountNumber,double balance,String password){
        super(accountNumber,balance,password);
    }

    public void deposit(double amount){
        balance+=amount;
    }

    public String withdraw(double amount,String password){

        if(!this.password.equals(password))
            return "Wrong password";

        if(amount>balance)
            return "Insufficient balance";

        balance-=amount;
        return "Withdraw successful";
    }
}

public class BankingGUI extends JFrame {

    static HashMap<Integer,BankAccount> accounts=new HashMap<>();
    static int accountCounter=1001;

    JTextField accField=new JTextField();
    JTextField amountField=new JTextField();
    JTextField passwordField=new JTextField();

    JTextArea output=new JTextArea();

    public BankingGUI(){

        setTitle("Banking MIS System");
        setSize(400,400);
        setLayout(new GridLayout(8,1));

        add(new JLabel("Account Number"));
        add(accField);

        add(new JLabel("Amount"));
        add(amountField);

        add(new JLabel("Password"));
        add(passwordField);

        JButton createBtn=new JButton("Create Account");
        JButton depositBtn=new JButton("Deposit");
        JButton withdrawBtn=new JButton("Withdraw");
        JButton balanceBtn=new JButton("Check Balance");

        add(createBtn);
        add(depositBtn);
        add(withdrawBtn);
        add(balanceBtn);

        add(new JScrollPane(output));

        createBtn.addActionListener(e->createAccount());
        depositBtn.addActionListener(e->deposit());
        withdrawBtn.addActionListener(e->withdraw());
        balanceBtn.addActionListener(e->checkBalance());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void createAccount(){

        String password=passwordField.getText();
        int acc=accountCounter++;

        accounts.put(acc,new SavingsAccount(acc,0,password));

        output.setText("Account created\nAccount No: "+acc);
    }

    void deposit(){

        int acc=Integer.parseInt(accField.getText());
        double amount=Double.parseDouble(amountField.getText());

        BankAccount a=accounts.get(acc);

        if(a==null){
            output.setText("Account not found");
            return;
        }

        a.deposit(amount);

        output.setText("Deposit successful");
    }

    void withdraw(){

        int acc=Integer.parseInt(accField.getText());
        double amount=Double.parseDouble(amountField.getText());
        String pass=passwordField.getText();

        BankAccount a=accounts.get(acc);

        if(a==null){
            output.setText("Account not found");
            return;
        }

        output.setText(a.withdraw(amount,pass));
    }

    void checkBalance(){

        int acc=Integer.parseInt(accField.getText());
        String pass=passwordField.getText();

        BankAccount a=accounts.get(acc);

        if(a==null){
            output.setText("Account not found");
            return;
        }

        output.setText(a.checkBalance(pass));
    }

    public static void main(String[] args){

        new BankingGUI();
    }
}