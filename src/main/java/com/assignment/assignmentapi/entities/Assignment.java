package com.assignment.assignmentapi.entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;

@Data
@Document(collection = "assignments")
public class Assignment {
    @Id
    private String id;
    @NotNull(message = "Each assignment must have exactly one technician")
    private String technicianId;
    private String startTime;
    private String endTime;
}