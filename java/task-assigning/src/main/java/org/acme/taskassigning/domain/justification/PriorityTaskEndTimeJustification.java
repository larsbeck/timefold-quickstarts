package org.acme.taskassigning.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.taskassigning.domain.Priority;
import org.acme.taskassigning.domain.Task;

public record PriorityTaskEndTimeJustification(String taskId, Priority priority, long endTime, String description)
        implements
            ConstraintJustification {

    public PriorityTaskEndTimeJustification {
        Objects.requireNonNull(taskId);
        Objects.requireNonNull(priority);
        Objects.requireNonNull(description);
    }

    public PriorityTaskEndTimeJustification(Task task) {
        this(task.getId(), task.getPriority(), task.getEndTime(),
                "%s priority task %s ends at minute %d.".formatted(task.getPriority(), task.getCode(),
                        task.getEndTime()));
    }
}
