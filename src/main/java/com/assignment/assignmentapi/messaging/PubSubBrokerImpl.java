package com.assignment.assignmentapi.messaging;
import com.assignment.assignmentapi.entities.Assignment;
import org.springframework.stereotype.Service;

@Service
public class PubSubBrokerImpl implements PubSubBroker {
    @Override
    public void publishAssignmentUpdate(Assignment assignment) {
        System.out.println("Publishing update to PubSub: " + assignment);
    }
}