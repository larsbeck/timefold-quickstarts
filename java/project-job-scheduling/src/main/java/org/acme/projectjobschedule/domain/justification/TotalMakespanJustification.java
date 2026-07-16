package org.acme.projectjobschedule.domain.justification;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record TotalMakespanJustification(int maxEndDate, String description) implements ConstraintJustification {

    public TotalMakespanJustification(int maxEndDate) {
        this(maxEndDate, "The overall schedule makespan ends on day %d.".formatted(maxEndDate));
    }
}
