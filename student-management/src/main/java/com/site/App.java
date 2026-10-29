package com.site;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beansConfig.xml")) {

            Student regularStudent =
                    context.getBean("regularStudent", Student.class);

            Student extensionStudent =
                    context.getBean("extensionStudent", Student.class);

            regularStudent.getDetails();

            System.out.println();

            extensionStudent.getDetails();
        }
    }
}