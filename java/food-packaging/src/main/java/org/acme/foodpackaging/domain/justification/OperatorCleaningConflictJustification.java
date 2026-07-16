package org.acme.foodpackaging.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.foodpackaging.domain.Job;

public record OperatorCleaningConflictJustification(String operatorId, String jobId1, String jobId2,
        long overlapMinutes, String description) implements ConstraintJustification {

    public OperatorCleaningConflictJustification {
        Objects.requireNonNull(jobId1);
        Objects.requireNonNull(jobId2);
    }

    public OperatorCleaningConflictJustification(Job job1, Job job2, long overlapMinutes) {
        this(job1.getLineOperator() == null ? null : job1.getLineOperator().getId(),
                job1.getId(), job2.getId(), overlapMinutes,
                "Operator %s is assigned to jobs %s and %s which overlap by %d minutes during cleaning time."
                        .formatted(job1.getLineOperator() == null ? "?" : job1.getLineOperator().getId(),
                                job1.getId(), job2.getId(), overlapMinutes));
    }
}
