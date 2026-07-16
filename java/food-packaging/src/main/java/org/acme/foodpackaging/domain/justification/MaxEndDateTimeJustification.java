package org.acme.foodpackaging.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.foodpackaging.domain.Job;

public record MaxEndDateTimeJustification(String jobId, long overdueMinutes, String description)
        implements
            ConstraintJustification {

    public MaxEndDateTimeJustification {
        Objects.requireNonNull(jobId);
    }

    public MaxEndDateTimeJustification(Job job, long overdueMinutes) {
        this(job.getId(), overdueMinutes,
                "Job %s finishes %d minutes after its maximum end time %s."
                        .formatted(job.getId(), overdueMinutes, job.getMaxEndTime()));
    }
}
