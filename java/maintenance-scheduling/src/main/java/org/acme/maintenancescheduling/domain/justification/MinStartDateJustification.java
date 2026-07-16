package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record MinStartDateJustification(String jobId, int daysTooEarly, String description)
        implements
            ConstraintJustification {

    public MinStartDateJustification {
        Objects.requireNonNull(jobId);
    }

    public MinStartDateJustification(Job job, int daysTooEarly) {
        this(job.getId(), daysTooEarly,
                "Job %s starts on %s, which is %d day(s) before its earliest allowed start date %s.".formatted(
                        job.getId(), job.getStartDate(), daysTooEarly, job.getMinStartDate()));
    }
}
