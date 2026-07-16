package org.acme.employeescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.employeescheduling.domain.Shift;

public record RequiredSkillJustification(String shiftId, String employeeName, String requiredSkill,
        String description) implements ConstraintJustification {

    public RequiredSkillJustification {
        Objects.requireNonNull(shiftId);
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(requiredSkill);
        Objects.requireNonNull(description);
    }

    public RequiredSkillJustification(Shift shift) {
        this(shift.getId(), shift.getEmployee().getName(), shift.getRequiredSkill(),
                "Employee %s lacks the required skill %s for shift %s.".formatted(
                        shift.getEmployee().getName(), shift.getRequiredSkill(), shift.getId()));
    }
}
