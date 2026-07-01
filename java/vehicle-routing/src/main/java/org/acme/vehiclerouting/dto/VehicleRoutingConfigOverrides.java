package org.acme.vehiclerouting.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.vehiclerouting.solver.VehicleRoutingConstraintProvider;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of tunable constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record VehicleRoutingConfigOverrides(
        @ConstraintReference(VehicleRoutingConstraintProvider.MAXIMIZE_VISITS_ASSIGNED) @Schema(
                description = "Medium weight of the maximize visits assigned constraint.") long maximizeVisitsAssignedWeight,
        @ConstraintReference(VehicleRoutingConstraintProvider.MINIMIZE_TRAVEL_TIME) @Schema(
                description = "Soft weight of the minimize travel time constraint.") long minimizeTravelTimeWeight)
        implements
            ModelConfigOverrides {

    public VehicleRoutingConfigOverrides {
        maximizeVisitsAssignedWeight = Math.max(0L, maximizeVisitsAssignedWeight);
        minimizeTravelTimeWeight = Math.max(0L, minimizeTravelTimeWeight);
    }

    public VehicleRoutingConfigOverrides() {
        this(1L, 1L);
    }

    public VehicleRoutingConfigOverrides withMaximizeVisitsAssignedWeight(long maximizeVisitsAssignedWeight) {
        return new VehicleRoutingConfigOverrides(maximizeVisitsAssignedWeight, minimizeTravelTimeWeight);
    }

    public VehicleRoutingConfigOverrides withMinimizeTravelTimeWeight(long minimizeTravelTimeWeight) {
        return new VehicleRoutingConfigOverrides(maximizeVisitsAssignedWeight, minimizeTravelTimeWeight);
    }
}
