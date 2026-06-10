import java.io.*;
import java.util.*;

public class StudentFileManager {
    public void saveStudents(List<Student> students, String filename) throws IOException {
        FileWriter writer = new FileWriter("students.txt");
        for (Student s : students) {
            writer.write(s.name + "," + s.grade + "\n");
        }
        writer.close();
    }

    public List<Student> loadStudents(String filename) throws FileNotFoundException, IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int grade = Integer.parseInt(parts[1]);
            students.add(new Student(name, grade));
        }
        reader.close();
        return students;
    }
}
