package com.edu.student.rest;

import com.edu.student.model.Student;
import com.edu.student.model.StudentDetail;
import com.edu.student.service.StudentSearchService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("student")
@Api(value = "student")
public class StudentResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentSearchService studentSearchService;

    @GET
    @Path("ping")
    @Produces({MediaType.TEXT_PLAIN})
    public String ping (@Context HttpHeaders headers) {
        return "Up";
    }

    @GET
    @Path("_search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Student> search (@QueryParam("first") String first, @QueryParam("last") String last) {
        try {
            return studentSearchService.search(first, last);
        } catch (Exception e) {
            logger.error("Call Failed", e);
            throw new ServerErrorException(500, e);
        }
    }

    @GET
    @Path("{first}/{last}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public StudentDetail studentDetail(@PathParam("first") String first, @PathParam("last") String last) {
        try {
            StudentDetail studentDetail = studentSearchService.studentDetail(first, last);
            if (null == studentDetail) {
                throw new NotFoundException();
            }
            return studentDetail;

        } catch (NotFoundException e) {
            logger.error("Not Found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Call Failed", e);
            throw new ServerErrorException(500, e);
        }
    }

}
