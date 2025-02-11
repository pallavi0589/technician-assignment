package com.assignment.assignmentapi.messaging;

import com.assignment.assignmentapi.entities.Assignment;

public interface DownstreamNotifier {
    void notifySystems(Assignment assignment);
}
