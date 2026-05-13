package Behavioral;

public class CreditCardPayment implements PaymentStrategy{

    public void pay(double amount){
        System.out.println(amount+ " paid using Credit card..");
    }
}