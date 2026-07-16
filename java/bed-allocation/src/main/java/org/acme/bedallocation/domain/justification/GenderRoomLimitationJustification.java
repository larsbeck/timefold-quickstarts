package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record GenderRoomLimitationJustification(String stayId, String patientName, String patientGender,
        String roomGenderLimitation, int nightCount, String description) implements ConstraintJustification {

    public GenderRoomLimitationJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public GenderRoomLimitationJustification(Stay stay) {
        this(stay.getId(), stay.getPatientName(), String.valueOf(stay.getPatientGender()),
                String.valueOf(stay.getRoomGenderLimitation()), stay.getNightCount(),
                "%s patient [%s] placed in a %s room for %d night(s)."
                        .formatted(stay.getPatientGender(), stay.getPatientName(), stay.getRoomGenderLimitation(),
                                stay.getNightCount()));
    }
}
