package org.acme.vehiclerouting.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record MinimizeTravelTimeJustification(String vehicleId, long totalDrivingTimeSeconds,
        String description) implements ConstraintJustification {

    public MinimizeTravelTimeJustification {
        Objects.requireNonNull(vehicleId);
    }

    public MinimizeTravelTimeJustification(String vehicleId, long totalDrivingTimeSeconds) {
        this(vehicleId, totalDrivingTimeSeconds,
                "Vehicle %s has a total driving time of %d second(s)."
                        .formatted(vehicleId, totalDrivingTimeSeconds));
    }
}
