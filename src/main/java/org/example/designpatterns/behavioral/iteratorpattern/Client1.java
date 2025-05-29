package org.example.designpatterns.behavioral.iteratorpattern;

public class Client1 {
    public static void main(String[] args) {
        StudentCollection1 students = new StudentCollection1();
        students.addStudent(new Student("John"));
        students.addStudent(new Student("Alice"));
        students.addStudent(new Student("Bob"));

        // The problem here is if the StudentCollection1 change and store the
        // students in a TreeSet instead of ArrayList... the below code will
        // break.
        for (int i = 0; i < students.getStudents().size(); i++) {
            System.out.println(students.getStudents().get(i));
        }
    }
}
