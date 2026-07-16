package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record MaxEndDateJustification(String jobId, int daysTooLate, String description)
        implements
            ConstraintJustification {

    public MaxEndDateJustification {
        Objects.requireNonNull(jobId);
    }

    public MaxEndDateJustification(Job job, int daysTooLate) {
        this(job.getId(), daysTooLate,
                "Job %s ends on %s, which is %d day(s) after its due date %s.".formatted(
                        job.getId(), job.getEndDate(), daysTooLate, job.getMaxEndDate()));
    }
}
