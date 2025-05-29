package org.example.designpatterns.behavioral.iteratorpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentCollection2 {
    List<Student> students = new ArrayList<>();
    public void addStudent(Student student){
        students.add(student);
    }

    public Iterator<Student> createIterator(){
        return new StudentIterator();
    }

    private class StudentIterator implements Iterator<Student> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return students.size() > cursor;
        }

        @Override
        public Student next() {
            return students.get(cursor++);
        }
    }


}