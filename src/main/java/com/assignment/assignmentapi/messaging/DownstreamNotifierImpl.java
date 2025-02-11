package com.assignment.assignmentapi.messaging;

import com.assignment.assignmentapi.entities.Assignment;
import org.springframework.stereotype.Service;

@Service
public class DownstreamNotifierImpl implements DownstreamNotifier {
    @Override
    public void notifySystems(Assignment assignment) {
        System.out.println("Notifying downstream systems: " + assignment);
    }
}