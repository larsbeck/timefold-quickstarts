package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.Employee;
import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record FirstAssignmentNotDepartingFromHomeJustification(String employeeName, String homeAirportCode,
        String flightAssignmentId, String departureAirportCode,
        String description) implements ConstraintJustification {

    public FirstAssignmentNotDepartingFromHomeJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(flightAssignmentId);
        Objects.requireNonNull(description);
    }

    public FirstAssignmentNotDepartingFromHomeJustification(Employee employee, FlightAssignment flightAssignment) {
        this(employee.getName(), employee.getHomeAirport().getCode(), flightAssignment.getId(),
                flightAssignment.getFlight().getDepartureAirport().getCode(),
                "Employee [%s]'s first flight [%s] departs from [%s] instead of their home airport [%s].".formatted(
                        employee.getName(), flightAssignment.getId(),
                        flightAssignment.getFlight().getDepartureAirport().getCode(),
                        employee.getHomeAirport().getCode()));
    }
}
