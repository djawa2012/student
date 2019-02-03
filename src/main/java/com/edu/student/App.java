package com.edu.student;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URI;


public class App {

    private static final URI BASE_URI = URI.create("http://0.0.0.0:8080");

    public static void main (String args[]) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        final JerseyConfig jerseyConfig = new JerseyConfig();
        jerseyConfig.property("contextConfig", ctx);

        try {
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, jerseyConfig, true);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
