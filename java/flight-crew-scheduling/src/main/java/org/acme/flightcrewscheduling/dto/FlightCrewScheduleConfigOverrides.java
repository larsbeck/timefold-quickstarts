package org.acme.flightcrewscheduling.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.flightcrewscheduling.solver.FlightCrewSchedulingConstraintProvider;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of soft constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record FlightCrewScheduleConfigOverrides(
        @ConstraintReference(FlightCrewSchedulingConstraintProvider.FIRST_ASSIGNMENT_NOT_DEPARTING_FROM_HOME) //
        @Schema(description = "Soft weight of the first assignment not departing from home constraint.") //
        long firstAssignmentNotDepartingFromHomeWeight,
        @ConstraintReference(FlightCrewSchedulingConstraintProvider.LAST_ASSIGNMENT_NOT_ARRIVING_AT_HOME) //
        @Schema(description = "Soft weight of the last assignment not arriving at home constraint.") //
        long lastAssignmentNotArrivingAtHomeWeight)
        implements
            ModelConfigOverrides {

    public FlightCrewScheduleConfigOverrides {
        firstAssignmentNotDepartingFromHomeWeight = Math.max(0L, firstAssignmentNotDepartingFromHomeWeight);
        lastAssignmentNotArrivingAtHomeWeight = Math.max(0L, lastAssignmentNotArrivingAtHomeWeight);
    }

    public FlightCrewScheduleConfigOverrides() {
        this(1L, 1L);
    }

    public FlightCrewScheduleConfigOverrides withFirstAssignmentNotDepartingFromHomeWeight(
            long firstAssignmentNotDepartingFromHomeWeight) {
        return new FlightCrewScheduleConfigOverrides(firstAssignmentNotDepartingFromHomeWeight,
                lastAssignmentNotArrivingAtHomeWeight);
    }

    public FlightCrewScheduleConfigOverrides withLastAssignmentNotArrivingAtHomeWeight(
            long lastAssignmentNotArrivingAtHomeWeight) {
        return new FlightCrewScheduleConfigOverrides(firstAssignmentNotDepartingFromHomeWeight,
                lastAssignmentNotArrivingAtHomeWeight);
    }
}
