package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import static java.util.stream.Collectors.joining;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record RequiredEquipmentJustification(String stayId, String roomId, String missingEquipments,
        int nightCount, String description) implements ConstraintJustification {

    public RequiredEquipmentJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public RequiredEquipmentJustification(Stay stay) {
        this(stay.getId(), stay.getRoom() == null ? null : stay.getRoom().getId(),
                stay.getPatientRequiredEquipments().stream()
                        .filter(e -> stay.getRoom() == null || !stay.getRoom().getEquipments().contains(e))
                        .collect(joining(", ")),
                stay.getNightCount(),
                "Room [%s] is missing required equipment [%s] for patient [%s] over %d night(s)."
                        .formatted(stay.getRoom() == null ? "?" : stay.getRoom().getId(),
                                stay.getPatientRequiredEquipments().stream()
                                        .filter(e -> stay.getRoom() == null || !stay.getRoom().getEquipments().contains(e))
                                        .collect(joining(", ")),
                                stay.getPatientName(), stay.getNightCount()));
    }
}
