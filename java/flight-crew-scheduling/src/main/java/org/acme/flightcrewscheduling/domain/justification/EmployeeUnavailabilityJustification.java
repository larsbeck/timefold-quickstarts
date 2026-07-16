package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record EmployeeUnavailabilityJustification(String flightAssignmentId, String employeeName,
        String description) implements ConstraintJustification {

    public EmployeeUnavailabilityJustification {
        Objects.requireNonNull(flightAssignmentId);
        Objects.requireNonNull(description);
    }

    public EmployeeUnavailabilityJustification(FlightAssignment flightAssignment) {
        this(flightAssignment.getId(),
                flightAssignment.getEmployee() == null ? null : flightAssignment.getEmployee().getName(),
                "Employee [%s] is assigned to flight [%s] departing on [%s] while unavailable.".formatted(
                        flightAssignment.getEmployee() == null ? "unassigned" : flightAssignment.getEmployee().getName(),
                        flightAssignment.getId(),
                        flightAssignment.getFlight().getDepartureUTCDate()));
    }
}
