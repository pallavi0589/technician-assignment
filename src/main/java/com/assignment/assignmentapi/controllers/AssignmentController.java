package com.assignment.assignmentapi.controllers;

import com.assignment.assignmentapi.entities.Assignment;
import com.assignment.assignmentapi.repositories.AssignmentRepository;
import com.assignment.assignmentapi.repositories.TechnicianRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/assignments")
@Tag(name = "Assignment API", description = "Operations related to Assignment management")
public class AssignmentController {
    private final AssignmentRepository repository;
     private final TechnicianRepository technicianRepository;
    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);


    public AssignmentController(AssignmentRepository repository, TechnicianRepository technicianRepository) {
        this.repository = repository;
        this.technicianRepository = technicianRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new Assignment", description = "Adds a new assignment with a linked technician. Ensures the technician exists.")
    public ResponseEntity<?> createAssignment(@Valid @RequestBody Assignment assignment) {
        logger.info("Attempting to create assignment: {}", assignment);
        
        if (assignment.getTechnicianId() == null || assignment.getTechnicianId().isEmpty()) {
            logger.error("Attempted to create an assignment without a technician");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Each assignment must have exactly one valid technician.");
        }
        
        // Ensure technician exists before creating assignment
        boolean technicianExists = technicianRepository.findById(assignment.getTechnicianId()).isPresent();
        if (!technicianExists) {
            logger.error("Technician with ID {} does not exist", assignment.getTechnicianId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Technician with ID " + assignment.getTechnicianId() + " does not exist.");
        }
        
        Assignment savedAssignment = repository.save(assignment);
        logger.info("Assignment created successfully: {}", savedAssignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignment);
    }


    
    @GetMapping("/{id}")
    @Operation(summary = "Get Assignment by ID", description = "Fetches an assignment record based on the provided ID")
    public Assignment getAssignment(@PathVariable String id) {
        logger.info("Fetching assignment with ID {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.error("Assignment with ID {} not found", id);
            return new RuntimeException("Assignment not found");
        });
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Assignment", description = "Updates an existing assignment and ensures a technician is assigned")
    public Assignment updateAssignment(@PathVariable String id, @Valid @RequestBody Assignment assignment) {
        if (!repository.existsById(id)) {
            logger.error("Attempted to update a non-existing assignment with ID {}", id);
            throw new RuntimeException("Assignment ID not found");
        }
        if (assignment.getTechnicianId() == null || assignment.getTechnicianId().isEmpty()) {
            logger.error("Attempted to update an assignment without a technician");
            throw new RuntimeException("Each assignment must have exactly one technician");
        }
        assignment.setId(id);
        logger.info("Updating assignment with ID {}: {}", id, assignment);
        return repository.save(assignment);
    }
}
