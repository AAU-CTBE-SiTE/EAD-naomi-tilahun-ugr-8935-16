package com.site;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {

    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");

    // CREATE
    public static void createStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            em.persist(student);

            transaction.commit();

            System.out.println("Student created successfully!");
            System.out.println(student);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error creating student:");
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // READ
    public static Student getStudentById(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Student student = em.find(Student.class, id);

            if (student != null) {
                System.out.println("Student found: " + student);
            } else {
                System.out.println(
                        "Student with ID " + id + " not found."
                );
            }

            return student;

        } finally {
            em.close();
        }
    }

    // UPDATE
    public static void updateStudentName(String newName, int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                student.setName(newName);

                transaction.commit();

                System.out.println("Student name updated successfully!");
                System.out.println(student);

            } else {
                transaction.rollback();

                System.out.println(
                        "Student with ID " + id + " not found."
                );
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error updating student:");
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // UPDATE
    public static void updateStudentNameAndTech(
            String newName, String newTech, int id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                student.setName(newName);
                student.setTech(newTech);

                transaction.commit();

                System.out.println(
                        "Student name and technology updated successfully!"
                );

                System.out.println(student);

            } else {
                transaction.rollback();

                System.out.println(
                        "Student with ID " + id + " not found."
                );
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error updating student:");
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    // DELETE
    public static void deleteStudentById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Student student = em.find(Student.class, id);

            if (student != null) {
                em.remove(student);

                transaction.commit();

                System.out.println("Student deleted successfully!");

            } else {
                transaction.rollback();

                System.out.println(
                        "Student with ID " + id + " not found."
                );
            }

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Error deleting student:");
            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {

        try {
            // 1. CREATE: Naomi - Java
            System.out.println("\n========== CREATE FIRST STUDENT ==========");

            Student firstStudent = new Student("Naomi", "Java");

            createStudent(firstStudent);

            int firstStudentId = firstStudent.getId();

            // 2. UPDATE: Naomi Java to Naomi Tilahun C++
            System.out.println("\n========== UPDATE FIRST STUDENT ==========");

            updateStudentNameAndTech(
                    "Naomi Tilahun",
                    "C++",
                    firstStudentId
            );

            System.out.println("\nVerifying the updated student:");
            getStudentById(firstStudentId);

            // 3.CREATE Yanet Tilahun - Python
            System.out.println("\n========== CREATE SECOND STUDENT ==========");

            Student secondStudent =
                    new Student("Yanet Tilahun", "Python");

            createStudent(secondStudent);

            int secondStudentId = secondStudent.getId();


            System.out.println("\nVerifying the second student:");
            getStudentById(secondStudentId);

            // 4.DELETE :Delete the first student created
            System.out.println("\n========== DELETE FIRST STUDENT ==========");

            deleteStudentById(firstStudentId);


            System.out.println("\n========== VERIFY DELETION ==========");

            getStudentById(firstStudentId);

            System.out.println("\n========== VERIFY SECOND STUDENT ==========");

            getStudentById(secondStudentId);

            System.out.println("\nAll requested CRUD operations completed!");

        } finally {
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}

