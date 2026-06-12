public class FullTimeEmployee implements Payable {
    public String name;
    private double monthlySalary;

    FullTimeEmployee(String name, double monthlySalary){
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public String getName() {
        return name;
    }

    public double calculatePay(){
        return getMonthlySalary();
    }
}
