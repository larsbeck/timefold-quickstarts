package org.acme.foodpackaging.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.foodpackaging.domain.Job;

public record JobNotAssignedJustification(String jobId, long durationMinutes, String description)
        implements
            ConstraintJustification {

    public JobNotAssignedJustification {
        Objects.requireNonNull(jobId);
    }

    public JobNotAssignedJustification(Job job, long durationMinutes) {
        this(job.getId(), durationMinutes,
                "Job %s (%d minutes) is not assigned to any line."
                        .formatted(job.getId(), durationMinutes));
    }
}
