import java.util.*;

public class StudentRegistry {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String name, int grade) {
        students.add(new Student(name, grade));
    }

    public void printAll(){
        for (Student s : students){
            System.out.println("Student name: " + s.name + " | Grade= " + s.grade);;
        }
    }

    public void printTopStudents() {
        for (Student s : students){
            if (s.grade >= 70) {
                System.out.println("Student name: " + s.name + " | Grade= " + s.grade);
            }
        }

    }

    public double getAverageGrade() {
        int sum = 0;
        for (Student s : students){
            sum += s.grade;
        }
        return (double) sum / students.size();
    }

}
