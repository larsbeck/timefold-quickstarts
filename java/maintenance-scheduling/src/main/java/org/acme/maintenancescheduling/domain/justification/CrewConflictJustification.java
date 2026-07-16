package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record CrewConflictJustification(String crewId, String job1Id, String job2Id, int overlapInDays,
        String description) implements ConstraintJustification {

    public CrewConflictJustification {
        Objects.requireNonNull(crewId);
        Objects.requireNonNull(job1Id);
        Objects.requireNonNull(job2Id);
    }

    public CrewConflictJustification(Job job1, Job job2, int overlapInDays) {
        this(job1.getCrew().getId(), job1.getId(), job2.getId(), overlapInDays,
                "Crew %s is assigned to overlapping jobs %s and %s for %d day(s) at the same time.".formatted(
                        job1.getCrew().getName(), job1.getId(), job2.getId(), overlapInDays));
    }
}
