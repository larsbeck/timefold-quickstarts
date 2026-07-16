package org.acme.vehiclerouting.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record UnassignedVisitJustification(String visitId, long serviceDurationInMinutes,
        String description) implements ConstraintJustification {

    public UnassignedVisitJustification {
        Objects.requireNonNull(visitId);
    }

    public UnassignedVisitJustification(String visitId, long serviceDurationInMinutes) {
        this(visitId, serviceDurationInMinutes,
                "Visit %s is not assigned to any vehicle (service duration of %d minute(s))."
                        .formatted(visitId, serviceDurationInMinutes));
    }
}
