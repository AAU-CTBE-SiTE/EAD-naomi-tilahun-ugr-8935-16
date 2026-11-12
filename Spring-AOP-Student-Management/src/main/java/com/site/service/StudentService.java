package com.site.service;

import com.site.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private Student[] students = new Student[10];
    private int size = 0;

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null!");
        }

        if (size >= students.length) {
            throw new RuntimeException("Student storage is full!");
        }

        students[size] = student;
        size++;

        System.out.println("\nStudent added successfully!");
    }

    public Student displayStudent(int id) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                System.out.println("\nStudent Found!");
                System.out.println(students[i]);
                return students[i];
            }
        }

        throw new RuntimeException("Student with ID " + id + " was not found!");
    }

    public void displayAllStudents() {
        System.out.println("\n ALL STUDENTS ");

        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
            System.out.println("--------------------------------");
        }
    }
}
