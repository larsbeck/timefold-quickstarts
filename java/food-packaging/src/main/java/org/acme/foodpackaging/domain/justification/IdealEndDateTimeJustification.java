package org.acme.foodpackaging.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.foodpackaging.domain.Job;

public record IdealEndDateTimeJustification(String jobId, long overdueMinutes, String description)
        implements
            ConstraintJustification {

    public IdealEndDateTimeJustification {
        Objects.requireNonNull(jobId);
    }

    public IdealEndDateTimeJustification(Job job, long overdueMinutes) {
        this(job.getId(), overdueMinutes,
                "Job %s finishes %d minutes after its ideal end time %s."
                        .formatted(job.getId(), overdueMinutes, job.getIdealEndTime()));
    }
}
