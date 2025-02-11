package com.assignment.assignmentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Assignment API", version = "1.0", description = "API for managing assignments and technicians"))
public class AssignmentApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(AssignmentApiApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Assignment API Application...");
        SpringApplication.run(AssignmentApiApplication.class, args);
        logger.info("Assignment API Application Started Successfully.");
    }
}