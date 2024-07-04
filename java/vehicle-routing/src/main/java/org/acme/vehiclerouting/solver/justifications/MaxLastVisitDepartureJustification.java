package org.acme.vehiclerouting.solver.justifications;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record MaxLastVisitDepartureJustification(String vehicleId, long lastVisitDepartureDelayInMinutes,
                                                 String description) implements ConstraintJustification {

    public MaxLastVisitDepartureJustification(String vehicleId, long lastVisitDepartureDelayInMinutes) {
        this(vehicleId, lastVisitDepartureDelayInMinutes, "Vehicle '%s' departed with a %s-minute delay."
                .formatted(vehicleId, lastVisitDepartureDelayInMinutes));
    }
}
