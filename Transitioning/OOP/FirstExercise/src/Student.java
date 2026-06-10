public class Student {
    public String name;
    public int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getStatus() {
        if (grade >= 50) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}