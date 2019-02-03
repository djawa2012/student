package com.edu.student;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.message.MessageProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.edu.student");
        register(LoggingFeature.class);
        packages("io.swagger.jaxrs.listing");

        register(ApiListingResource.class);
        property(MessageProperties.XML_FORMAT_OUTPUT, true);
        property(ServerProperties.TRACING, "ALL");

    }
}
