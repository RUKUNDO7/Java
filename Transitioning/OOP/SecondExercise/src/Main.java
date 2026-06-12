public class Main {
    public static void main(String[] args) {
        BankAccount one = new BankAccount("Smith", 1000000.00);
        BankAccount two = new BankAccount("Alex", 7000000.00);

        one.deposit(400000.00);
        System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        two.deposit(900000.00);
        System.out.println("New Balance for " + two.getOwnerName() + " = " + two.getBalance());

        one.withdraw(1000000000000000000.00);
        System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        one.withdraw(40000.00);
        System.out.println("New Balance for " + one.getOwnerName() + " = " + one.getBalance());
        two.withdraw(500000.00);
        System.out.println("New Balance for " + two.getOwnerName() + " = " + two.getBalance());
    }
}