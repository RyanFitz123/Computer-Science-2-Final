import java.util.ArrayList;

public class User {
    
    private String username;
    private String password;
    private double balance;
    private boolean suspended;
    private ArrayList<String> transactionHistory;

    User(String username, String password){
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.suspended = false;
        this.transactionHistory = new ArrayList<>();
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int Withdraw(double amount){
        //code to withdraw amount
        if(amount > 0 && amount <= balance){
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount + " | New Balance: $" + balance);

            return 1; //success
        }

        return 0; //failure
    }

    public void Deposit(double amount){
        //code to deposit amount
        balance += amount;
        transactionHistory.add("Deposited: $" + amount + " | New Balance: $" + balance);
    }

    public double getBalance(){
        return balance;
    }

    public void Suspend(){
        suspended = true;
    }

    public void Unsuspend(){
        suspended = false;
    }

    public boolean isSuspended() {
        return suspended;
    }

}
