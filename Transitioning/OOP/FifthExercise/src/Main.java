import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 40));
        students.add(new Student("Bob", 50));
        students.add(new Student("Smith", 10));
        students.add(new Student("Alex", 51));

        StudentFileManager manager = new StudentFileManager();

        for (Student s : students) {
            System.out.println(s.name + " - " + s.grade + " - " + s.getStatus());
        }

        try {
            manager.saveStudents(students, "students.txt");
            List<Student> studentsLoaded = manager.loadStudents("students.txt");
            for (Student s : studentsLoaded) {
                System.out.println(s.name + " , " + s.grade);
            }
        } catch (Exception e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}