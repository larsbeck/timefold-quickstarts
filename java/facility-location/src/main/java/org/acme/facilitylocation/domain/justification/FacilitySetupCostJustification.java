package org.acme.facilitylocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.facilitylocation.domain.Facility;

public record FacilitySetupCostJustification(String facilityId, long setupCost,
        String description) implements ConstraintJustification {

    public FacilitySetupCostJustification {
        Objects.requireNonNull(facilityId);
        Objects.requireNonNull(description);
    }

    public FacilitySetupCostJustification(Facility facility) {
        this(facility.getId(), facility.getSetupCost(),
                "Facility %s is used, incurring a setup cost of %d."
                        .formatted(facility.getId(), facility.getSetupCost()));
    }
}
