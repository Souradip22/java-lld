package org.example.designpatterns.behavioral.iteratorpattern;

import java.util.Iterator;

public class Client3 {
    public static void main(String[] args) {
        StudentCollection3 students = new StudentCollection3();
        students.addStudent(new Student("John 3"));
        students.addStudent(new Student("Alice 3"));
        students.addStudent(new Student("Bob 3"));

        // Note the library can use anything to store the collection
        Iterator<Student> studentIterator = students.iterator();
        while (studentIterator.hasNext()){
            System.out.println(studentIterator.next());
        }
    }
}