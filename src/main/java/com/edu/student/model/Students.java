package com.edu.student.model;

import com.edu.student.dao.StudentDO;

import java.util.List;
import java.util.Map;

public class Students {

    private List<StudentDO> students;
    private Map<Integer, String> classes;

    public List<StudentDO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDO> students) {
        this.students = students;
    }

    public Map<Integer, String> getClasses() {
        return classes;
    }

    public void setClasses(Map<Integer, String> classes) {
        this.classes = classes;
    }
}
