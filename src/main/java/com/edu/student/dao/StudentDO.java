package com.edu.student.dao;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.List;

@Document(collection = "students", schemaVersion = "1.0")
public class StudentDO {

    @Id
    private String email;
    private String first;
    private String last;
    private List<ClassDO> studentClasses;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ClassDO> getStudentClasses() {
        return studentClasses;
    }

    public void setStudentClasses(List<ClassDO> studentClasses) {
        this.studentClasses = studentClasses;
    }
}
