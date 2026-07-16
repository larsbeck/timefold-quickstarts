package org.acme.taskassigning.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.taskassigning.domain.Task;

public record MissingSkillsJustification(String taskId, String employeeName, int missingSkillCount, String description)
        implements
            ConstraintJustification {

    public MissingSkillsJustification {
        Objects.requireNonNull(taskId);
        Objects.requireNonNull(description);
    }

    public MissingSkillsJustification(Task task) {
        this(task.getId(), task.getEmployee() == null ? null : task.getEmployee().getFullName(),
                task.getMissingSkillCount(),
                "Task %s assigned to employee %s is missing %d required skill(s).".formatted(task.getCode(),
                        task.getEmployee() == null ? "unassigned" : task.getEmployee().getFullName(),
                        task.getMissingSkillCount()));
    }
}
