package org.acme.employeescheduling.domain.justification;

import java.time.LocalDate;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record UnavailableEmployeeJustification(String employeeName, String shiftId, LocalDate date,
        int overlapMinutes, String description) implements ConstraintJustification {

    public UnavailableEmployeeJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(shiftId);
        Objects.requireNonNull(date);
        Objects.requireNonNull(description);
    }

    public UnavailableEmployeeJustification(Shift shift, LocalDate date, int overlapMinutes) {
        this(shift.getEmployee().getName(), shift.getId(), date, overlapMinutes,
                "Employee %s is assigned shift %s for %d minutes on unavailable date %s.".formatted(
                        shift.getEmployee().getName(), shift.getId(), overlapMinutes, date));
    }
}
