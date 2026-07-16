package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record PreferredMaximumRoomCapacityJustification(String stayId, Integer preferredMaximumRoomCapacity,
        int roomCapacity, int nightCount, String description) implements ConstraintJustification {

    public PreferredMaximumRoomCapacityJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public PreferredMaximumRoomCapacityJustification(Stay stay) {
        this(stay.getId(), stay.getPatientPreferredMaximumRoomCapacity(),
                stay.getRoom() == null ? 0 : stay.getRoom().getCapacity(), stay.getNightCount(),
                "Patient [%s] prefers a room capacity of at most %d but is in a room of capacity %d for %d night(s)."
                        .formatted(stay.getPatientName(), stay.getPatientPreferredMaximumRoomCapacity(),
                                stay.getRoom() == null ? 0 : stay.getRoom().getCapacity(), stay.getNightCount()));
    }
}
