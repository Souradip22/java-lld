package org.example.designpatterns.behavioral.iteratorpattern;

import java.util.ArrayList;
import java.util.List;

public class StudentCollection1 {
    List<Student> students = new ArrayList<>();
    public void addStudent(Student student){
        students.add(student);
    }

    public List<Student> getStudents(){
        return students;
    }


}
