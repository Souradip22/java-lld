package org.example.designpatterns.behavioral.iteratorpattern;

public class Student implements Comparable<Student>{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
