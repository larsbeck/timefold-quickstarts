package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record FlightConflictJustification(String employeeName, String flightAssignmentId1, String flightAssignmentId2,
        String description) implements ConstraintJustification {

    public FlightConflictJustification {
        Objects.requireNonNull(flightAssignmentId1);
        Objects.requireNonNull(flightAssignmentId2);
        Objects.requireNonNull(description);
    }

    public FlightConflictJustification(FlightAssignment flightAssignment1, FlightAssignment flightAssignment2) {
        this(flightAssignment1.getEmployee() == null ? null : flightAssignment1.getEmployee().getName(),
                flightAssignment1.getId(), flightAssignment2.getId(),
                "Employee [%s] is assigned to two overlapping flights [%s (%s - %s)] and [%s (%s - %s)].".formatted(
                        flightAssignment1.getEmployee() == null ? "unassigned"
                                : flightAssignment1.getEmployee().getName(),
                        flightAssignment1.getId(), flightAssignment1.getDepartureUTCDateTime(),
                        flightAssignment1.getFlight().getArrivalUTCDateTime(),
                        flightAssignment2.getId(), flightAssignment2.getDepartureUTCDateTime(),
                        flightAssignment2.getFlight().getArrivalUTCDateTime()));
    }
}
