package org.acme.employeescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record OverlappingShiftJustification(String employeeName, String firstShiftId, String secondShiftId,
        int overlapMinutes, String description) implements ConstraintJustification {

    public OverlappingShiftJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(firstShiftId);
        Objects.requireNonNull(secondShiftId);
        Objects.requireNonNull(description);
    }

    public OverlappingShiftJustification(Shift firstShift, Shift secondShift, int overlapMinutes) {
        this(firstShift.getEmployee().getName(), firstShift.getId(), secondShift.getId(), overlapMinutes,
                "Employee %s is assigned overlapping shifts %s and %s overlapping by %d minutes.".formatted(
                        firstShift.getEmployee().getName(), firstShift.getId(), secondShift.getId(), overlapMinutes));
    }
}
