package com.edu.student.service;

import com.edu.student.dao.StudentDAO;
import com.edu.student.dao.StudentDO;
import com.edu.student.decorator.StudentDOMapper;
import com.edu.student.model.Student;
import com.edu.student.model.StudentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class StudentSearchService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentDOMapper studentMapper;

    public List<Student> search (String first, String last) {
        List<StudentDO> studentList;

        if ( first == null && last == null ) {
            studentList = Collections.emptyList();
        } else if ( null == last ) {
            studentList = studentDAO.searchByFirst(first);
        } else if ( null == first ) {
            studentList = studentDAO.searchByLast(last);
        } else {
            studentList = studentDAO.search(first, last);
        }

        return studentMapper.mapStudentList(studentList);
    }

    public StudentDetail studentDetail (String first, String last) {
        Optional<StudentDO> studentDOOptional =
                studentDAO.getStudent(first, last);

        if ( studentDOOptional.isPresent() ) {
            return studentMapper.mapStudentDetail(studentDOOptional.get());
        }
        return null;
    }
}
