public class BankAccount {
    private String ownerName;
    private double balance;

    BankAccount(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount <= 0) {
            System.out.println("Insufficient amount");
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0){
            System.out.println("Insufficient amount");
        } else if(balance < amount) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
        }

    }
}
