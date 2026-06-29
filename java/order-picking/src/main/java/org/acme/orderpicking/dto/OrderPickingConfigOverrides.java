package org.acme.orderpicking.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.orderpicking.solver.OrderPickingConstraintProvider;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of soft constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record OrderPickingConfigOverrides(
        @ConstraintReference(OrderPickingConstraintProvider.MINIMIZE_DISTANCE_FROM_PREVIOUS_PICK) @Schema(
                description = "Soft weight of the minimize distance from previous pick constraint.") long minimizeDistanceFromPreviousPickWeight,
        @ConstraintReference(OrderPickingConstraintProvider.MINIMIZE_DISTANCE_TO_PATH_ORIGIN) @Schema(
                description = "Soft weight of the minimize distance to the path origin constraint.") long minimizeDistanceToPathOriginWeight,
        @ConstraintReference(OrderPickingConstraintProvider.MINIMIZE_ORDER_SPLIT_BY_TROLLEY) @Schema(
                description = "Soft weight of the minimize order split by trolley constraint.") long minimizeOrderSplitByTrolleyWeight)
        implements
            ModelConfigOverrides {

    public OrderPickingConfigOverrides {
        minimizeDistanceFromPreviousPickWeight = Math.max(0L, minimizeDistanceFromPreviousPickWeight);
        minimizeDistanceToPathOriginWeight = Math.max(0L, minimizeDistanceToPathOriginWeight);
        minimizeOrderSplitByTrolleyWeight = Math.max(0L, minimizeOrderSplitByTrolleyWeight);
    }

    public OrderPickingConfigOverrides() {
        this(1L, 1L, 1L);
    }

    public OrderPickingConfigOverrides withMinimizeDistanceFromPreviousPickWeight(long minimizeDistanceFromPreviousPickWeight) {
        return new OrderPickingConfigOverrides(minimizeDistanceFromPreviousPickWeight, minimizeDistanceToPathOriginWeight,
                minimizeOrderSplitByTrolleyWeight);
    }

    public OrderPickingConfigOverrides withMinimizeDistanceToPathOriginWeight(long minimizeDistanceToPathOriginWeight) {
        return new OrderPickingConfigOverrides(minimizeDistanceFromPreviousPickWeight, minimizeDistanceToPathOriginWeight,
                minimizeOrderSplitByTrolleyWeight);
    }

    public OrderPickingConfigOverrides withMinimizeOrderSplitByTrolleyWeight(long minimizeOrderSplitByTrolleyWeight) {
        return new OrderPickingConfigOverrides(minimizeDistanceFromPreviousPickWeight, minimizeDistanceToPathOriginWeight,
                minimizeOrderSplitByTrolleyWeight);
    }
}
