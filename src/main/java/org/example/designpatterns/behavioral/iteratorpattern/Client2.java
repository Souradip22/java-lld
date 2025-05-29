package org.example.designpatterns.behavioral.iteratorpattern;

import java.util.Iterator;

public class Client2 {
    public static void main(String[] args) {
        StudentCollection2 students = new StudentCollection2();
        students.addStudent(new Student("John 2"));
        students.addStudent(new Student("Alice 2"));
        students.addStudent(new Student("Bob 2"));

        // Note the library can use anything to store the collection
        Iterator<Student> studentIterator = students.createIterator();
        while (studentIterator.hasNext()){
            System.out.println(studentIterator.next());
        }
    }
}