package org.acme.projectjobschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.projectjobschedule.domain.Allocation;

public record TotalProjectDelayJustification(String allocationId, int endDate, int criticalPathEndDate,
        int projectDelay, String description) implements ConstraintJustification {

    public TotalProjectDelayJustification {
        Objects.requireNonNull(allocationId);
    }

    public TotalProjectDelayJustification(Allocation allocation) {
        this(allocation.getId(), allocation.getEndDate(), allocation.getProjectCriticalPathEndDate(),
                allocation.getProjectDelay(),
                "Project of allocation [%s] finishes %d day(s) after its critical path end date %d (end date %d)."
                        .formatted(allocation.getId(), allocation.getProjectDelay(),
                                allocation.getProjectCriticalPathEndDate(), allocation.getEndDate()));
    }
}
