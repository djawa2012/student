package com.edu.student.decorator;

import com.edu.student.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseFactory {

    public Course createCource () {
        return new Course();
    }
}
