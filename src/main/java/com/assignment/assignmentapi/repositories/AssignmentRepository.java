package com.assignment.assignmentapi.repositories;
import com.assignment.assignmentapi.entities.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {}