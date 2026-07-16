package org.acme.taskassigning.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.taskassigning.domain.Employee;

public record MakespanJustification(String employeeId, long endTime, String description)
        implements
            ConstraintJustification {

    public MakespanJustification {
        Objects.requireNonNull(employeeId);
        Objects.requireNonNull(description);
    }

    public MakespanJustification(Employee employee) {
        this(employee.getId(), employee.getEndTime(),
                "Employee %s finishes their last task at minute %d.".formatted(employee.getFullName(),
                        employee.getEndTime()));
    }
}
