package org.acme.employeescheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record BalanceEmployeeShiftAssignmentsJustification(long unfairness,
        String description) implements ConstraintJustification {

    public BalanceEmployeeShiftAssignmentsJustification {
        Objects.requireNonNull(description);
    }

    public BalanceEmployeeShiftAssignmentsJustification(long unfairness) {
        this(unfairness,
                "Shift assignments are unbalanced across employees with an unfairness measure of %d."
                        .formatted(unfairness));
    }
}
