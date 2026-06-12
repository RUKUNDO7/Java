public class FreelancerEmployee implements Payable {
    public String name;
    public int hoursWorked;
    public double hourlyRate;

    FreelancerEmployee(String name, int hoursWorked, double hourlyRate) {
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }

    public String getName() {
        return name;
    }
}
