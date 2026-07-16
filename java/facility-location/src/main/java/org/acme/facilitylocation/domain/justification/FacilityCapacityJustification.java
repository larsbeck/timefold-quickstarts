package org.acme.facilitylocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.facilitylocation.domain.Facility;

public record FacilityCapacityJustification(String facilityId, long demand, long capacity, long overCapacityBy,
        String description) implements ConstraintJustification {

    public FacilityCapacityJustification {
        Objects.requireNonNull(facilityId);
        Objects.requireNonNull(description);
    }

    public FacilityCapacityJustification(Facility facility, long demand) {
        this(facility.getId(), demand, facility.getCapacity(), demand - facility.getCapacity(),
                "Facility %s is assigned demand %d, exceeding its capacity %d by %d."
                        .formatted(facility.getId(), demand, facility.getCapacity(), demand - facility.getCapacity()));
    }
}
