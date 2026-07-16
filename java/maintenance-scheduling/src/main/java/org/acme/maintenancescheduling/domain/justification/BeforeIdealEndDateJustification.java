package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record BeforeIdealEndDateJustification(String jobId, int daysBeforeIdeal, String description)
        implements
            ConstraintJustification {

    public BeforeIdealEndDateJustification {
        Objects.requireNonNull(jobId);
    }

    public BeforeIdealEndDateJustification(Job job, int daysBeforeIdeal) {
        this(job.getId(), daysBeforeIdeal,
                "Job %s ends on %s, which is %d day(s) before its ideal end date %s.".formatted(
                        job.getId(), job.getEndDate(), daysBeforeIdeal, job.getIdealEndDate()));
    }
}
