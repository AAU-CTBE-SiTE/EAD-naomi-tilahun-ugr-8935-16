package com.site.service;

import com.site.model.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StudentService {

    private Student[] students = new Student[100];
    private int size = 0;
    private int nextId = 1;


    public Student[] getAllStudents() {
        return Arrays.copyOf(students, size);
    }


    public Student getStudentById(int id) {

        for (int i = 0; i < size; i++) {

            if (students[i].getId() == id) {
                return students[i];
            }
        }

        return null;
    }


    public Student addStudent(Student student) {

        if (size >= students.length) {
            throw new RuntimeException("Student storage is full.");
        }

        student.setId(nextId++);

        students[size] = student;
        size++;

        return student;
    }


    public Student updateStudent(int id, Student updatedStudent) {

        for (int i = 0; i < size; i++) {

            if (students[i].getId() == id) {

                updatedStudent.setId(id);
                students[i] = updatedStudent;

                return students[i];
            }
        }

        return null;
    }


    public Student patchStudent(int id, Student updatedStudent) {

        for (int i = 0; i < size; i++) {

            if (students[i].getId() == id) {

                if (updatedStudent.getName() != null) {
                    students[i].setName(updatedStudent.getName());
                }

                if (updatedStudent.getDepartment() != null) {
                    students[i].setDepartment(updatedStudent.getDepartment());
                }

                if (updatedStudent.getYear() != null) {
                    students[i].setYear(updatedStudent.getYear());
                }

                return students[i];
            }
        }

        return null;
    }


    public boolean deleteStudent(int id) {

        for (int i = 0; i < size; i++) {

            if (students[i].getId() == id) {


                for (int j = i; j < size - 1; j++) {
                    students[j] = students[j + 1];
                }

                students[size - 1] = null;
                size--;

                return true;
            }
        }

        return false;
    }
}
