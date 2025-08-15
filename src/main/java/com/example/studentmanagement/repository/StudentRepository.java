package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by email
    Optional<Student> findByEmail(String email);

    // Find students by first name
    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    // Find students by last name
    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    // Find students by grade
    List<Student> findByGrade(String grade);

    // Find students with GPA greater than specified value
    List<Student> findByGpaGreaterThan(Double gpa);

    // Custom query to find students by name (first or last)
    @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> findByNameContainingIgnoreCase(@Param("name") String name);

    // Check if email exists
    boolean existsByEmail(String email);
}
