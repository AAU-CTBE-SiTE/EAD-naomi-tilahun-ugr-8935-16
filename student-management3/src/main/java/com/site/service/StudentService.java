package com.site.studentmanagement3.service;

import com.site.studentmanagement3.model.Student;
import com.site.studentmanagement3.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // CREATE
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // READ ONE
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with ID: " + id));
    }

    // UPDATE - PUT
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setYear(updatedStudent.getYear());
        existingStudent.setCourse(updatedStudent.getCourse());
        existingStudent.setGrade(updatedStudent.getGrade());

        return studentRepo.save(existingStudent);
    }

    // PARTIAL UPDATE - PATCH
    public Student patchStudent(Long id, Student updatedStudent) {

        Student existingStudent = getStudentById(id);

        if (updatedStudent.getName() != null) {
            existingStudent.setName(updatedStudent.getName());
        }

        if (updatedStudent.getDepartment() != null) {
            existingStudent.setDepartment(updatedStudent.getDepartment());
        }

        if (updatedStudent.getYear() != 0) {
            existingStudent.setYear(updatedStudent.getYear());
        }

        if (updatedStudent.getCourse() != null) {
            existingStudent.setCourse(updatedStudent.getCourse());
        }

        if (updatedStudent.getGrade() != null) {
            existingStudent.setGrade(updatedStudent.getGrade());
        }

        return studentRepo.save(existingStudent);
    }

    // DELETE
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepo.delete(student);
    }
}
