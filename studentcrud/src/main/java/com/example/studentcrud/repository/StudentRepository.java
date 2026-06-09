package com.example.studentcrud.repository;

import com.example.studentcrud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // JPQL Query
    @Query("SELECT s FROM Student s WHERE s.age > ?1")
    List<Student> findStudentsOlderThan(int age);

    // JPQL LIKE Query
    @Query("SELECT s FROM Student s WHERE s.name LIKE CONCAT('%', ?1, '%')")
    List<Student> searchStudentsByName(String name);

    // Native SQL Query
    @Query(
            value = "SELECT * FROM students WHERE email = ?1",
            nativeQuery = true
    )
    Student findStudentByEmail(String email);
}