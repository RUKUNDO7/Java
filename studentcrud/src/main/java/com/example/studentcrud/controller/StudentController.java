package com.example.studentcrud.controller;

import com.example.studentcrud.dto.ApiResponse;
import com.example.studentcrud.model.Student;
import com.example.studentcrud.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ApiResponse<Student> createStudent(
            @RequestBody Student student
    ) {

        Student createdStudent = service.createStudent(student);

        return new ApiResponse<>(
                "Student created successfully",
                createdStudent
        );
    }

    // READ ALL
    @GetMapping
    public ApiResponse<List<Student>> getAllStudents() {

        List<Student> students = service.getAllStudents();

        return new ApiResponse<>(
                "Students fetched successfully",
                students
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ApiResponse<Student> getStudentById(
            @PathVariable Long id
    ) {

        Student student = service.getStudentById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found")
                );

        return new ApiResponse<>(
                "Student fetched successfully",
                student
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApiResponse<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) {

        Student updatedStudent = service.updateStudent(id, student);

        return new ApiResponse<>(
                "Student updated successfully",
                updatedStudent
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStudent(
            @PathVariable Long id
    ) {

        service.deleteStudent(id);

        return new ApiResponse<>(
                "Student deleted successfully",
                null
        );
    }

    // JPQL QUERY
    @GetMapping("/older-than/{age}")
    public ApiResponse<List<Student>> getStudentsOlderThan(
            @PathVariable int age
    ) {

        List<Student> students =
                service.getStudentsOlderThan(age);

        return new ApiResponse<>(
                "Students fetched successfully",
                students
        );
    }

    // SEARCH BY NAME
    @GetMapping("/search/{name}")
    public ApiResponse<List<Student>> searchStudentsByName(
            @PathVariable String name
    ) {

        List<Student> students =
                service.searchStudentsByName(name);

        return new ApiResponse<>(
                "Students found successfully",
                students
        );
    }

    // NATIVE QUERY
    @GetMapping("/email/{email}")
    public ApiResponse<Student> getStudentByEmail(
            @PathVariable String email
    ) {

        Student student =
                service.getStudentByEmail(email);

        return new ApiResponse<>(
                "Student fetched successfully",
                student
        );
    }

    @GetMapping("/paginated")
    public ApiResponse<Page<Student>> getStudentsPaginated(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy
    ) {

        Page<Student> students =
                service.getStudentsPaginated(
                        page,
                        size,
                        sortBy
                );

        return new ApiResponse<>(
                "Students fetched successfully",
                students
        );
    }
}