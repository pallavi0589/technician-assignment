package com.assignment.assignmentapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "technicians")
public class Technician {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
