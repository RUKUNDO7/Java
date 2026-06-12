public class Main {
    public static void main(String[] args) {
        StudentRegistry one = new StudentRegistry();
        one.addStudent("Alex", 89);
        one.addStudent("Smith", 54);
        one.addStudent("Bob", 67);
        one.addStudent("Jane", 87);
        one.addStudent("Bill", 77);

        one.printAll();
        one.printTopStudents();
        System.out.println("Average = " + one.getAverageGrade());
    }
}