package com.edu.student.rest;

import com.edu.student.Config;
import com.edu.student.JerseyConfig;
import com.edu.student.model.Course;
import com.edu.student.model.Student;
import com.edu.student.model.StudentDetail;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import java.util.List;

public class StudentResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        return new JerseyConfig().property("contextConfig", ctx);
    }

    @Test
    public void testPing() throws Exception {
        final String ping = target("student/ping")
                .request()
                .get(String.class);
        Assert.assertEquals("Up", ping);
    }

    @Test
    public void testSearchFirst () {
        List<Student> studentList = target("student/_search")
                .queryParam("first", "Jo")
                .request()
                .get(List.class);

        Assert.assertEquals(3, studentList.size());
    }

    @Test
    public void testSearchLast () {
        List<Student> studentList = target("student/_search")
                .queryParam("last", "sm")
                .request()
                .get(List.class);

        Assert.assertEquals(2, studentList.size());
    }

    @Test
    public void testSearchNoResult () {
        List<Student> studentList = target("student/_search")
                .queryParam("first", "sm")
                .request()
                .get(List.class);

        Assert.assertEquals(0, studentList.size());
    }

    @Test
    public void testSearchFirstLast () {
        List<Student> studentList = target("student/_search")
                .queryParam("first", "John")
                .queryParam("last", "Smith")
                .request()
                .get(new GenericType<List<Student>>(){});

        Assert.assertEquals(1, studentList.size());

        Student student = studentList.get(0);
        Assert.assertEquals("John", student.getFirst());
        Assert.assertEquals("Smith", student.getLast());
        Assert.assertEquals( 3.17d, student.getGpa().doubleValue(), 0d);
    }

    @Test
    public void testStudentDetail () {
        StudentDetail studentDetail =
                target("student/john/smith")
                .request()
                .get(StudentDetail.class);

        Assert.assertNotNull(studentDetail);

        Assert.assertEquals("John", studentDetail.getFirst());
        Assert.assertEquals("Smith", studentDetail.getLast());
        Assert.assertEquals( 3.17d, studentDetail.getGpa().doubleValue(), 0d);
        Assert.assertEquals("johnsmith@mailinator.com", studentDetail.getEmail());
        Assert.assertEquals(6, studentDetail.getCourseList().size());
        List<Course> courseList = studentDetail.getCourseList();
        Assert.assertEquals("Math 101", courseList.get(0).getName());
        Assert.assertEquals(1, courseList.get(0).getId().intValue());
        Assert.assertEquals(4.0f, courseList.get(0).getGpa().floatValue(), 0f);
    }

    @Test(expected = NotFoundException.class)
    public void testStudentDetailNotFound () {
        StudentDetail studentDetail =
                target("student/john/smith2")
                        .request()
                        .get(StudentDetail.class);

        System.out.println(studentDetail);
    }
}