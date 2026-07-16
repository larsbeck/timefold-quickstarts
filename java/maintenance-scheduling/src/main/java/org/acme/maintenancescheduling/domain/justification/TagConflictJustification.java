package org.acme.maintenancescheduling.domain.justification;

import java.util.Objects;
import java.util.Set;

import static java.lang.String.join;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.maintenancescheduling.domain.Job;

public record TagConflictJustification(String job1Id, String job2Id, Set<String> sharedTags, int overlapInDays,
        String description) implements ConstraintJustification {

    public TagConflictJustification {
        Objects.requireNonNull(job1Id);
        Objects.requireNonNull(job2Id);
        Objects.requireNonNull(sharedTags);
    }

    public TagConflictJustification(Job job1, Job job2, Set<String> sharedTags, int overlapInDays) {
        this(job1.getId(), job2.getId(), sharedTags, overlapInDays,
                "Jobs %s and %s overlap for %d day(s) while sharing tag(s) [%s].".formatted(
                        job1.getId(), job2.getId(), overlapInDays, join(", ", sharedTags)));
    }
}
