import java.util.*;

abstract class BankAccount {

    protected int accountNumber;
    protected double balance;
    protected String password;
    protected List<String> transactions = new ArrayList<>();

    public BankAccount(int accountNumber, double balance, String password) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.password = password;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount, String password);

    public void checkBalance(String password) {

        if (this.password.equals(password)) {
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Access Denied!");
        }
    }

    public void showTransactions(String password) {

        if (this.password.equals(password)) {

            System.out.println("Transaction History:");

            for (String t : transactions) {
                System.out.println(t);
            }

        } else {
            System.out.println("Access Denied!");
        }
    }
}


class SavingsAccount extends BankAccount {

    public SavingsAccount(int accountNumber, double balance, String password) {
        super(accountNumber, balance, password);
    }

    public void deposit(double amount) {

        balance += amount;
        transactions.add("Deposited: " + amount);

        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount, String password) {

        if (this.password.equals(password)) {

            if (amount <= balance) {

                balance -= amount;
                transactions.add("Withdrawn: " + amount);

                System.out.println("Withdrawn: " + amount);

            } else {
                System.out.println("Insufficient balance!");
            }

        } else {
            System.out.println("Incorrect password!");
        }
    }
}


class CurrentAccount extends BankAccount {

    public CurrentAccount(int accountNumber, double balance, String password) {
        super(accountNumber, balance, password);
    }

    public void deposit(double amount) {

        balance += amount;
        transactions.add("Deposited: " + amount);

        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount, String password) {

        if (this.password.equals(password)) {

            if (amount <= balance) {

                balance -= amount;
                transactions.add("Withdrawn: " + amount);

                System.out.println("Withdrawn: " + amount);

            } else {
                System.out.println("Insufficient balance!");
            }

        } else {
            System.out.println("Incorrect password!");
        }
    }
}


public class BankingMIS {

    static HashMap<Integer, BankAccount> accounts = new HashMap<>();
    static int accountCounter = 1001;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Banking MIS System =====");
            System.out.println("1 Create Account");
            System.out.println("2 Deposit");
            System.out.println("3 Withdraw");
            System.out.println("4 Check Balance");
            System.out.println("5 View Transactions");
            System.out.println("6 Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("Choose Account Type (1 Savings / 2 Current)");
                    int type = sc.nextInt();

                    System.out.println("Enter initial balance:");
                    double balance = sc.nextDouble();

                    System.out.println("Set password:");
                    String password = sc.next();

                    int accNo = accountCounter++;

                    if (type == 1) {

                        accounts.put(accNo,
                                new SavingsAccount(accNo, balance, password));

                    } else {

                        accounts.put(accNo,
                                new CurrentAccount(accNo, balance, password));
                    }

                    System.out.println("Account Created Successfully!");
                    System.out.println("Your Account Number: " + accNo);

                    break;


                case 2:

                    System.out.println("Enter Account Number:");
                    int accDeposit = sc.nextInt();

                    BankAccount acc1 = accounts.get(accDeposit);

                    if (acc1 != null) {

                        System.out.println("Enter amount:");
                        double amount = sc.nextDouble();

                        acc1.deposit(amount);

                    } else {
                        System.out.println("Account not found!");
                    }

                    break;


                case 3:

                    System.out.println("Enter Account Number:");
                    int accWithdraw = sc.nextInt();

                    BankAccount acc2 = accounts.get(accWithdraw);

                    if (acc2 != null) {

                        System.out.println("Enter amount:");
                        double amount = sc.nextDouble();

                        System.out.println("Enter password:");
                        String pass = sc.next();

                        acc2.withdraw(amount, pass);

                    } else {
                        System.out.println("Account not found!");
                    }

                    break;


                case 4:

                    System.out.println("Enter Account Number:");
                    int accCheck = sc.nextInt();

                    BankAccount acc3 = accounts.get(accCheck);

                    if (acc3 != null) {

                        System.out.println("Enter password:");
                        String pass = sc.next();

                        acc3.checkBalance(pass);

                    } else {
                        System.out.println("Account not found!");
                    }

                    break;


                case 5:

                    System.out.println("Enter Account Number:");
                    int accTrans = sc.nextInt();

                    BankAccount acc4 = accounts.get(accTrans);

                    if (acc4 != null) {

                        System.out.println("Enter password:");
                        String pass = sc.next();

                        acc4.showTransactions(pass);

                    } else {
                        System.out.println("Account not found!");
                    }

                    break;


                case 6:

                    System.out.println("Thank you for using Banking MIS");
                    return;


                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}