package com.edu.student;

import com.edu.student.dao.ClassesDAO;
import com.edu.student.dao.StudentDO;
import com.edu.student.model.Students;
import com.edu.student.util.JsonToPOJO;
import io.jsondb.JsonDBTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class DBInit {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String DATA_SET = "student_courses.json";
    private static String DB_NAME = "students";

    @Autowired
    private JsonDBTemplate jsonDBTemplate;

    @Autowired
    private JsonToPOJO jsonToPOJO;

    @Autowired
    private ClassesDAO classesDAO;

    @PostConstruct
    public void initialize () {
        if ( !jsonDBTemplate.collectionExists(DB_NAME) ) {
            jsonDBTemplate.createCollection(StudentDO.class);
            logger.info("Student database initialized");
        } else {
            logger.info("Student database already initialized");
        }

        Optional<Students> studentsOptional = jsonToPOJO.mapResourceAsPOJO(DATA_SET, Students.class);
        if ( studentsOptional.isPresent() ) {
            Students students = studentsOptional.get();
            logger.info(String.format("Going to upsert %s student(s)", students.getStudents().size()));
            for (StudentDO student : students.getStudents()) {
                jsonDBTemplate.upsert(student);
            }
            classesDAO.setClassesMap(students.getClasses());
        }
    }
}
