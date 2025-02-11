package com.assignment.assignmentapi.repositories;
import com.assignment.assignmentapi.entities.Technician;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechnicianRepository extends MongoRepository<Technician, String> {}
