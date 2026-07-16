package org.acme.taskassigning.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.taskassigning.domain.Task;

public record UnassignedTaskJustification(String taskId, String description) implements ConstraintJustification {

    public UnassignedTaskJustification {
        Objects.requireNonNull(taskId);
        Objects.requireNonNull(description);
    }

    public UnassignedTaskJustification(Task task) {
        this(task.getId(), "Task %s is not assigned to any employee.".formatted(task.getCode()));
    }
}
