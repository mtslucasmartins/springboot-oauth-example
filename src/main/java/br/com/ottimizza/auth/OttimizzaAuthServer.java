package br.com.ottimizza.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OttimizzaAuthServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OttimizzaAuthServer.class, args);
    }

}
