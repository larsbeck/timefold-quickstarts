package org.acme.taskassigning.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.taskassigning.solver.TaskAssigningConstraintProvider;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of soft constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record TaskAssigningConfigOverrides(
        @ConstraintReference(TaskAssigningConstraintProvider.MINIMIZE_UNASSIGNED_TASKS) @Schema(
                description = "Soft weight of the minimize unassigned tasks constraint.") long minimizeUnassignedTasksWeight,
        @ConstraintReference(TaskAssigningConstraintProvider.MINIMIZE_MAKESPAN) @Schema(
                description = "Soft weight of the minimize makespan constraint.") long minimizeMakespanWeight,
        @ConstraintReference(TaskAssigningConstraintProvider.CRITICAL_PRIORITY_TASK_END_TIME) @Schema(
                description = "Soft weight of the critical priority task end time constraint.") long criticalPriorityWeight,
        @ConstraintReference(TaskAssigningConstraintProvider.MAJOR_PRIORITY_TASK_END_TIME) @Schema(
                description = "Soft weight of the major priority task end time constraint.") long majorPriorityWeight,
        @ConstraintReference(TaskAssigningConstraintProvider.MINOR_PRIORITY_TASK_END_TIME) @Schema(
                description = "Soft weight of the minor priority task end time constraint.") long minorPriorityWeight)
        implements
            ModelConfigOverrides {

    public TaskAssigningConfigOverrides {
        minimizeUnassignedTasksWeight = Math.max(0L, minimizeUnassignedTasksWeight);
        minimizeMakespanWeight = Math.max(0L, minimizeMakespanWeight);
        criticalPriorityWeight = Math.max(0L, criticalPriorityWeight);
        majorPriorityWeight = Math.max(0L, majorPriorityWeight);
        minorPriorityWeight = Math.max(0L, minorPriorityWeight);
    }

    public TaskAssigningConfigOverrides() {
        this(1L, 1L, 1L, 1L, 1L);
    }

    public TaskAssigningConfigOverrides withMinimizeUnassignedTasksWeight(long minimizeUnassignedTasksWeight) {
        return new TaskAssigningConfigOverrides(minimizeUnassignedTasksWeight, minimizeMakespanWeight, criticalPriorityWeight,
                majorPriorityWeight, minorPriorityWeight);
    }

    public TaskAssigningConfigOverrides withMinimizeMakespanWeight(long minimizeMakespanWeight) {
        return new TaskAssigningConfigOverrides(minimizeUnassignedTasksWeight, minimizeMakespanWeight, criticalPriorityWeight,
                majorPriorityWeight, minorPriorityWeight);
    }

    public TaskAssigningConfigOverrides withCriticalPriorityWeight(long criticalPriorityWeight) {
        return new TaskAssigningConfigOverrides(minimizeUnassignedTasksWeight, minimizeMakespanWeight, criticalPriorityWeight,
                majorPriorityWeight, minorPriorityWeight);
    }

    public TaskAssigningConfigOverrides withMajorPriorityWeight(long majorPriorityWeight) {
        return new TaskAssigningConfigOverrides(minimizeUnassignedTasksWeight, minimizeMakespanWeight, criticalPriorityWeight,
                majorPriorityWeight, minorPriorityWeight);
    }

    public TaskAssigningConfigOverrides withMinorPriorityWeight(long minorPriorityWeight) {
        return new TaskAssigningConfigOverrides(minimizeUnassignedTasksWeight, minimizeMakespanWeight, criticalPriorityWeight,
                majorPriorityWeight, minorPriorityWeight);
    }
}
