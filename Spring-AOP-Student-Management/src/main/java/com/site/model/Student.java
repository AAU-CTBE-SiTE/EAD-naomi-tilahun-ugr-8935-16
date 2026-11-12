
package com.site.model;

public class Student {

    private int id;
    private String name;
    private String department;
    private int year;
    private String course;
    private String grade;

    public Student() {
    }

    public Student(int id, String name, String department,
                   int year, String course, String grade) {

        this.id = id;
        this.name = name;
        this.department = department;
        this.year = year;
        this.course = course;
        this.grade = grade;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }



    @Override
    public String toString() {

        return "\nStudent ID: " + id +
                "\nName: " + name +
                "\nDepartment: " + department +
                "\nYear: " + year +
                "\nCourse: " + course +
                "\nGrade: " + grade;
    }
}
