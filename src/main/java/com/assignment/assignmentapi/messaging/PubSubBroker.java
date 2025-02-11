package com.assignment.assignmentapi.messaging;

import com.assignment.assignmentapi.entities.Assignment;

public interface PubSubBroker {
    void publishAssignmentUpdate(Assignment assignment);
}