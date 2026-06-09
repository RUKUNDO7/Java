package com.example.studentcrud.service;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // READ ONE
    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {

        return repository.findById(id)
                .map(student -> {

                    student.setName(updatedStudent.getName());
                    student.setAge(updatedStudent.getAge());
                    student.setEmail(updatedStudent.getEmail());

                    return repository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // DELETE
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    // JPQL QUERY
    public List<Student> getStudentsOlderThan(int age) {
        return repository.findStudentsOlderThan(age);
    }

    // SEARCH BY NAME
    public List<Student> searchStudentsByName(String name) {
        return repository.searchStudentsByName(name);
    }

    // NATIVE QUERY
    public Student getStudentByEmail(String email) {
        return repository.findStudentByEmail(email);
    }

    public Page<Student> getStudentsPaginated(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        return repository.findAll(pageable);
    }
}