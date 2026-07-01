package org.acme.projectjobschedule.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.projectjobschedule.solver.ProjectJobScheduleConstraintProperties;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record ProjectJobScheduleConfigOverrides(
        @ConstraintReference(ProjectJobScheduleConstraintProperties.TOTAL_PROJECT_DELAY) @Schema(
                description = "Medium weight of the total project delay constraint.") long totalProjectDelayWeight,
        @ConstraintReference(ProjectJobScheduleConstraintProperties.TOTAL_MAKESPAN) @Schema(
                description = "Soft weight of the total makespan constraint.") long totalMakespanWeight)
        implements
            ModelConfigOverrides {

    public ProjectJobScheduleConfigOverrides {
        totalProjectDelayWeight = Math.max(0L, totalProjectDelayWeight);
        totalMakespanWeight = Math.max(0L, totalMakespanWeight);
    }

    public ProjectJobScheduleConfigOverrides() {
        this(1L, 1L);
    }

    public ProjectJobScheduleConfigOverrides withTotalProjectDelayWeight(long totalProjectDelayWeight) {
        return new ProjectJobScheduleConfigOverrides(totalProjectDelayWeight, totalMakespanWeight);
    }

    public ProjectJobScheduleConfigOverrides withTotalMakespanWeight(long totalMakespanWeight) {
        return new ProjectJobScheduleConfigOverrides(totalProjectDelayWeight, totalMakespanWeight);
    }
}
