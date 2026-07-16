package org.acme.employeescheduling.domain.justification;

import java.time.LocalDate;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record DesiredDayJustification(String employeeName, String shiftId, LocalDate date,
        int overlapMinutes, String description) implements ConstraintJustification {

    public DesiredDayJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(shiftId);
        Objects.requireNonNull(date);
        Objects.requireNonNull(description);
    }

    public DesiredDayJustification(Shift shift, LocalDate date, int overlapMinutes) {
        this(shift.getEmployee().getName(), shift.getId(), date, overlapMinutes,
                "Employee %s is assigned shift %s for %d minutes on desired date %s.".formatted(
                        shift.getEmployee().getName(), shift.getId(), overlapMinutes, date));
    }
}
