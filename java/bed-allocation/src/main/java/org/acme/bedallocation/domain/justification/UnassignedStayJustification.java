package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record UnassignedStayJustification(String stayId, String patientName, int nightCount,
        String description) implements ConstraintJustification {

    public UnassignedStayJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public UnassignedStayJustification(Stay stay) {
        this(stay.getId(), stay.getPatientName(), stay.getNightCount(),
                "Patient [%s] stay is not assigned to any bed for %d night(s)."
                        .formatted(stay.getPatientName(), stay.getNightCount()));
    }
}
