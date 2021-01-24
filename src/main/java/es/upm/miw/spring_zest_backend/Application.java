package es.upm.miw.spring_zest_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class}) // Not: /error
public class Application {

    // mvn clean spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
