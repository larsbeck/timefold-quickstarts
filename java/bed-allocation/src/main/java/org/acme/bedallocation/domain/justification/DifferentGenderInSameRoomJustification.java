package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record DifferentGenderInSameRoomJustification(String stayId1, String stayId2, String roomId,
        int sameNightCount, String description) implements ConstraintJustification {

    public DifferentGenderInSameRoomJustification {
        Objects.requireNonNull(stayId1);
        Objects.requireNonNull(stayId2);
        Objects.requireNonNull(description);
    }

    public DifferentGenderInSameRoomJustification(Stay stay1, Stay stay2, int sameNightCount) {
        this(stay1.getId(), stay2.getId(), stay1.getRoom() == null ? null : stay1.getRoom().getId(), sameNightCount,
                "Patients of different genders [%s (%s), %s (%s)] share the same-gender room [%s] for %d night(s)."
                        .formatted(stay1.getPatientName(), stay1.getPatientGender(), stay2.getPatientName(),
                                stay2.getPatientGender(), stay1.getRoom() == null ? "?" : stay1.getRoom().getId(),
                                sameNightCount));
    }
}
