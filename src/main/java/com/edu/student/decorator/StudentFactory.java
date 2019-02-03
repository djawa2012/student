package com.edu.student.decorator;

import com.edu.student.model.Student;
import com.edu.student.model.StudentDetail;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {

    public Student createStudent () {
        return new Student();
    }

    public StudentDetail createStudentDetail () {
        return new StudentDetail();
    }
}
