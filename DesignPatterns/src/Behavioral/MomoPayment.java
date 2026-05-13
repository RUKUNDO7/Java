package Behavioral;

public class MomoPayment implements PaymentStrategy{

    public void pay(double amount){
        System.out.println(amount+ " paid using MOMO..");
    }
}