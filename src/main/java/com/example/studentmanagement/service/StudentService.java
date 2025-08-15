package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Get student by email
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Create new student
    public Student createStudent(Student student) {
        // Check if email already exists
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Student with email " + student.getEmail() + " already exists");
        }
        return studentRepository.save(student);
    }

    // Update existing student
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Check if email is being changed and if it already exists
        if (!student.getEmail().equals(studentDetails.getEmail()) && 
            studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new RuntimeException("Student with email " + studentDetails.getEmail() + " already exists");
        }

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setGrade(studentDetails.getGrade());
        student.setGpa(studentDetails.getGpa());

        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // Search students by name
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // Get students by grade
    public List<Student> getStudentsByGrade(String grade) {
        return studentRepository.findByGrade(grade);
    }

    // Get students with GPA above threshold
    public List<Student> getStudentsWithGpaAbove(Double gpa) {
        return studentRepository.findByGpaGreaterThan(gpa);
    }

    // Check if student exists
    public boolean studentExists(Long id) {
        return studentRepository.existsById(id);
    }
}
