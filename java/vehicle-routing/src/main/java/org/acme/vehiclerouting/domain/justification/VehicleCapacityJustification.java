package org.acme.vehiclerouting.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record VehicleCapacityJustification(String vehicleId, int capacity, int totalDemand, int overCapacityBy,
        String description) implements ConstraintJustification {

    public VehicleCapacityJustification {
        Objects.requireNonNull(vehicleId);
    }

    public VehicleCapacityJustification(String vehicleId, int capacity, int totalDemand, int overCapacityBy) {
        this(vehicleId, capacity, totalDemand, overCapacityBy,
                "Vehicle %s carries a total demand of %d, exceeding its capacity of %d by %d."
                        .formatted(vehicleId, totalDemand, capacity, overCapacityBy));
    }
}
