package com.site;

import com.site.config.AppConfig;
import com.site.model.Student;
import com.site.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService =
                context.getBean(StudentService.class);

        System.out.println("\n");
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("USING SPRING AOP");
        System.out.println("\n");


        Student student1 = new Student(
                101,
                "Naomi Tilahun",
                "Software Engineering",
                3,
                "Advanced Java Programming",
                "A"
        );

        Student student2 = new Student(
                102,
                "Hana Bekele",
                "Computer Science",
                2,
                "Database Management",
                "B"
        );


        System.out.println("\n ADD STUDENTS ");

        studentService.addStudent(student1);
        studentService.addStudent(student2);


        System.out.println("\n DISPLAY STUDENT ");

        studentService.displayStudent(101);


        System.out.println("\n DISPLAY ALL STUDENTS ");

        studentService.displayAllStudents();


        System.out.println("\n TEST ERROR HANDLING ");

        try {
            studentService.displayStudent(999);
        } catch (RuntimeException exception) {
            System.out.println("\nMain program caught the error:");
            System.out.println(exception.getMessage());
        }

        System.out.println("\n");
        System.out.println("PROGRAM COMPLETED");
        System.out.println("\n");


        context.close();
    }
}
