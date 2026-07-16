package org.acme.foodpackaging.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.foodpackaging.domain.Job;

public record MakespanJustification(String jobId, String lineId, long makespanMinutes, String description)
        implements
            ConstraintJustification {

    public MakespanJustification {
        Objects.requireNonNull(jobId);
    }

    public MakespanJustification(Job job, long makespanMinutes) {
        this(job.getId(), job.getLine() == null ? null : job.getLine().getId(), makespanMinutes,
                "Last job %s on line %s ends %d minutes after the line start."
                        .formatted(job.getId(), job.getLine() == null ? "?" : job.getLine().getId(), makespanMinutes));
    }
}
