package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record AfterIdealEndDateJustification(String jobId, int daysAfterIdeal, String description)
        implements
            ConstraintJustification {

    public AfterIdealEndDateJustification {
        Objects.requireNonNull(jobId);
    }

    public AfterIdealEndDateJustification(Job job, int daysAfterIdeal) {
        this(job.getId(), daysAfterIdeal,
                "Job %s ends on %s, which is %d day(s) after its ideal end date %s.".formatted(
                        job.getId(), job.getEndDate(), daysAfterIdeal, job.getIdealEndDate()));
    }
}
