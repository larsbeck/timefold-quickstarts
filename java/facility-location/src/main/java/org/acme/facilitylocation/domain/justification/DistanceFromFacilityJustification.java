package org.acme.facilitylocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.facilitylocation.domain.Consumer;

public record DistanceFromFacilityJustification(String consumerId, String facilityId, long distance,
        String description) implements ConstraintJustification {

    public DistanceFromFacilityJustification {
        Objects.requireNonNull(consumerId);
        Objects.requireNonNull(facilityId);
        Objects.requireNonNull(description);
    }

    public DistanceFromFacilityJustification(Consumer consumer) {
        this(consumer.getId(), consumer.getFacility().getId(), consumer.distanceFromFacility(),
                "Consumer %s is assigned to facility %s at a distance of %d."
                        .formatted(consumer.getId(), consumer.getFacility().getId(), consumer.distanceFromFacility()));
    }
}
