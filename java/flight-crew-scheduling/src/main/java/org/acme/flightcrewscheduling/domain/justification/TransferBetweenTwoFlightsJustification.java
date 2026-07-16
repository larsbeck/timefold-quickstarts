package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record TransferBetweenTwoFlightsJustification(String employeeName, String flightAssignmentId1,
        String arrivalAirportCode, String flightAssignmentId2, String departureAirportCode,
        String description) implements ConstraintJustification {

    public TransferBetweenTwoFlightsJustification {
        Objects.requireNonNull(flightAssignmentId1);
        Objects.requireNonNull(flightAssignmentId2);
        Objects.requireNonNull(description);
    }

    public TransferBetweenTwoFlightsJustification(FlightAssignment flightAssignment1,
            FlightAssignment flightAssignment2) {
        this(flightAssignment1.getEmployee() == null ? null : flightAssignment1.getEmployee().getName(),
                flightAssignment1.getId(), flightAssignment1.getFlight().getArrivalAirport().getCode(),
                flightAssignment2.getId(), flightAssignment2.getFlight().getDepartureAirport().getCode(),
                "Employee [%s] has consecutive flights that do not connect: flight [%s] arrives at [%s] but next flight [%s] departs from [%s]."
                        .formatted(
                                flightAssignment1.getEmployee() == null ? "unassigned"
                                        : flightAssignment1.getEmployee().getName(),
                                flightAssignment1.getId(),
                                flightAssignment1.getFlight().getArrivalAirport().getCode(),
                                flightAssignment2.getId(),
                                flightAssignment2.getFlight().getDepartureAirport().getCode()));
    }
}
