package com.edu.student.decorator;

import com.edu.student.dao.ClassDO;
import com.edu.student.dao.ClassesDAO;
import com.edu.student.dao.StudentDO;
import com.edu.student.model.Course;
import com.edu.student.model.Student;
import com.edu.student.model.StudentDetail;
import com.edu.student.util.RoundDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Component
public class StudentDOMapper {

    @Autowired
    private StudentFactory studentFactory;

    @Autowired
    private RoundDecimal roundDecimal;

    @Autowired
    private CourseFactory courseFactory;

    @Autowired
    private ClassesDAO classesDAO;

    public List<Student> mapStudentList (List<StudentDO> studentDOList) {
        List<Student> studentList = new ArrayList<>();

        for (StudentDO studentDO : studentDOList) {
            Student student = studentFactory.createStudent();
            mapStudent(studentDO, student);
            studentList.add(student);
        }

        return studentList;
    }

    public StudentDetail mapStudentDetail (StudentDO studentDO) {
        StudentDetail studentDetail = studentFactory.createStudentDetail();
        mapStudent(studentDO, (Student)studentDetail);

        studentDetail.setEmail( studentDO.getEmail() );

        List<Course> courseList = new ArrayList<>();
        for (ClassDO classDO : studentDO.getStudentClasses()) {
            Course course = courseFactory.createCource();
            mapClass(classDO, course);
            course.setName(classesDAO.getClassName(course.getId()));

            courseList.add(course);
        }
        studentDetail.setCourseList(courseList);

        return studentDetail;


    }

    private void mapStudent (StudentDO studentDO, Student student) {
        student.setFirst( studentDO.getFirst() );
        student.setLast( studentDO.getLast() );

        student.setGpa( calculateGPA(studentDO.getStudentClasses()) );
    }

    private void mapClass (ClassDO classDO, Course cls) {
        cls.setId( classDO.getId() );
        cls.setGpa( classDO.getGrade() );
    }

    private Double calculateGPA (List<ClassDO> classDOList) {
        OptionalDouble optionalDouble =
                classDOList.stream().mapToDouble( a -> a.getGrade() ).average();
        if ( optionalDouble.isPresent() ) {
            return roundDecimal.round2DecimalPoint(optionalDouble.getAsDouble());
        }
        return null;
    }
}
