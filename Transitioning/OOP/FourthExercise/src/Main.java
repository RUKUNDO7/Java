public class Main {
    public static void main(String[] args) {
        BankAccount one = new BankAccount("Smith", 1000000.00);
        BankAccount two = new BankAccount("Alex", 7000000.00);

        one.deposit(400000.00);
        System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        two.deposit(900000.00);
        System.out.println("New Balance for " + two.getOwnerName() + " = " + two.getBalance());

        try {
            one.withdraw(1000000000000000000.00);
            System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            one.withdraw(40000.00);
            System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            two.withdraw(500000.00);
            System.out.println("New Balance for " + two.getOwnerName() + " = " + two.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}