package com.edu.student.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClassesDAO {

    private Map<Integer, String> classesMap;

    public ClassesDAO() {
        this.classesMap = new HashMap<>();
    }

    public Map<Integer, String> getClassesMap() {
        return classesMap;
    }

    public void setClassesMap(Map<Integer, String> classesMap) {
        this.classesMap = classesMap;
    }

    public String getClassName (Integer id) {
        return classesMap.get(id);
    }
}
