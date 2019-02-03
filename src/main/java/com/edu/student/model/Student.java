package com.edu.student.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {

    private String first;
    private String last;
    private Double gpa;

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

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
