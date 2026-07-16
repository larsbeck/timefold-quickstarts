package org.acme.employeescheduling.domain.justification;

import java.time.LocalDate;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record OneShiftPerDayJustification(String employeeName, String firstShiftId, String secondShiftId,
        LocalDate date, String description) implements ConstraintJustification {

    public OneShiftPerDayJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(firstShiftId);
        Objects.requireNonNull(secondShiftId);
        Objects.requireNonNull(date);
        Objects.requireNonNull(description);
    }

    public OneShiftPerDayJustification(Shift firstShift, Shift secondShift) {
        this(firstShift.getEmployee().getName(), firstShift.getId(), secondShift.getId(),
                firstShift.getStart().toLocalDate(),
                "Employee %s is assigned two shifts %s and %s on the same day %s.".formatted(
                        firstShift.getEmployee().getName(), firstShift.getId(), secondShift.getId(),
                        firstShift.getStart().toLocalDate()));
    }
}
