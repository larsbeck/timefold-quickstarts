package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record SameBedJustification(String stayId1, String stayId2, String bedId, int sameNightCount,
        String description) implements ConstraintJustification {

    public SameBedJustification {
        Objects.requireNonNull(stayId1);
        Objects.requireNonNull(stayId2);
        Objects.requireNonNull(description);
    }

    public SameBedJustification(Stay stay1, Stay stay2, int sameNightCount) {
        this(stay1.getId(), stay2.getId(), stay1.getBed() == null ? null : stay1.getBed().getId(), sameNightCount,
                "Patients [%s, %s] occupy the same bed [%s] for %d night(s)."
                        .formatted(stay1.getPatientName(), stay2.getPatientName(),
                                stay1.getBed() == null ? "?" : stay1.getBed().getId(), sameNightCount));
    }
}
