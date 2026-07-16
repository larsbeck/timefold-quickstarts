package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.Employee;
import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record LastAssignmentNotArrivingAtHomeJustification(String employeeName, String homeAirportCode,
        String flightAssignmentId, String arrivalAirportCode,
        String description) implements ConstraintJustification {

    public LastAssignmentNotArrivingAtHomeJustification {
        Objects.requireNonNull(employeeName);
        Objects.requireNonNull(flightAssignmentId);
        Objects.requireNonNull(description);
    }

    public LastAssignmentNotArrivingAtHomeJustification(Employee employee, FlightAssignment flightAssignment) {
        this(employee.getName(), employee.getHomeAirport().getCode(), flightAssignment.getId(),
                flightAssignment.getFlight().getArrivalAirport().getCode(),
                "Employee [%s]'s last flight [%s] arrives at [%s] instead of their home airport [%s].".formatted(
                        employee.getName(), flightAssignment.getId(),
                        flightAssignment.getFlight().getArrivalAirport().getCode(),
                        employee.getHomeAirport().getCode()));
    }
}
