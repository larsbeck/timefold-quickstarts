package org.acme.employeescheduling.domain.justification;

import java.time.LocalDate;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record UndesiredDayJustification(String employeeName, String shiftId, LocalDate date,
        int overlapMinutes, String description) implements ConstraintJustification {

    public UndesiredDayJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(shiftId);
        Objects.requireNonNull(date);
        Objects.requireNonNull(description);
    }

    public UndesiredDayJustification(Shift shift, LocalDate date, int overlapMinutes) {
        this(shift.getEmployee().getName(), shift.getId(), date, overlapMinutes,
                "Employee %s is assigned shift %s for %d minutes on undesired date %s.".formatted(
                        shift.getEmployee().getName(), shift.getId(), overlapMinutes, date));
    }
}
