package org.acme.employeescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record RestBetweenShiftsJustification(String employeeName, String firstShiftId, String secondShiftId,
        long breakMinutes, String description) implements ConstraintJustification {

    public RestBetweenShiftsJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(firstShiftId);
        Objects.requireNonNull(secondShiftId);
        Objects.requireNonNull(description);
    }

    public RestBetweenShiftsJustification(Shift firstShift, Shift secondShift, long breakMinutes) {
        this(firstShift.getEmployee().getName(), firstShift.getId(), secondShift.getId(), breakMinutes,
                "Employee %s has only %d minutes rest between shifts %s and %s, less than the required 10 hours."
                        .formatted(firstShift.getEmployee().getName(), breakMinutes, firstShift.getId(),
                                secondShift.getId()));
    }
}
