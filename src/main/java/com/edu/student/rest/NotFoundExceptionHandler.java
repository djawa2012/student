package com.edu.student.rest;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(NotFoundException ex){
        return Response
                .status(404)
                .entity(new Message("Requested resource not found."))
                .type(getAcceptType())
                .build();
    }

    private String getAcceptType(){
        boolean xml = false;

        for(MediaType acceptableMediaType : headers.getAcceptableMediaTypes()) {
            if ( "xml".equalsIgnoreCase(acceptableMediaType.getSubtype()) ) {
                xml = true;
            }
        }

        if ( xml ) {
            return MediaType.APPLICATION_XML;
        } else {
            return MediaType.APPLICATION_JSON;
        }
    }
}