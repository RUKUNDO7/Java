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
        if(amount <= 0){
            throw new IllegalArgumentException();
        }
            balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException{
        if (balance < amount){
            throw new InsufficientFundsException("Insufficient funds for " + ownerName);
        }else if(amount <= 0){
            System.out.println("Insufficient amount");
        }else {
            balance -= amount;
        }

    }

}
