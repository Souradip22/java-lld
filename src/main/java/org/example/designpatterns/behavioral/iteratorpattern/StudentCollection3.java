package org.example.designpatterns.behavioral.iteratorpattern;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StudentCollection3 implements Iterable<Student> {
    Set<Student> students = new TreeSet<>(); // even if we change this to any
    // other container... it will work fine

    public void addStudent(Student student){
        students.add(student);
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }
}
