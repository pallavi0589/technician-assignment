package com.assignment.assignmentapi.controllers;

import com.assignment.assignmentapi.entities.Technician;
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
@RequestMapping("/technicians")
@Tag(name = "Technician API", description = "Operations related to Technician management")
public class TechnicianController {
    private final TechnicianRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TechnicianController.class);

    public TechnicianController(TechnicianRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Operation(summary = "Create a new Technician", description = "Adds a new technician to the database")
    public Technician createTechnician(@Valid @RequestBody Technician technician) {
        logger.info("Creating technician: {}", technician);
        return repository.save(technician);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Technician by ID", description = "Fetches a technician record based on the provided ID")
    public Technician getTechnician(@PathVariable String id) {
        logger.info("Fetching technician with ID {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.error("Technician with ID {} not found", id);
            return new RuntimeException("Technician not found");
        });
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Technician", description = "Updates an existing technician's details based on the provided ID")
    public Technician updateTechnician(@PathVariable String id, @Valid @RequestBody Technician technician) {
        logger.info("Updating technician with ID {}: {}", id, technician);
        technician.setId(id);
        return repository.save(technician);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Technician", description = "Deletes a technician record by ID. Returns 404 if not found.")
    public ResponseEntity<String> deleteTechnician(@PathVariable String id) {
    logger.info("Attempting to delete technician with ID {}", id);
    
    if (!repository.existsById(id)) {
        logger.error("Technician with ID {} not found", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Technician with ID " + id + " not found.");
    }

    repository.deleteById(id);
    logger.info("Technician with ID {} deleted successfully", id);
    return ResponseEntity.ok("Technician deleted successfully.");
 }
}
