package org.acme.flightcrewscheduling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.flightcrewscheduling.domain.FlightAssignment;

public record RequiredSkillJustification(String flightAssignmentId, String employeeName, String requiredSkill,
        String description) implements ConstraintJustification {

    public RequiredSkillJustification {
        Objects.requireNonNull(flightAssignmentId);
        Objects.requireNonNull(description);
    }

    public RequiredSkillJustification(FlightAssignment flightAssignment) {
        this(flightAssignment.getId(),
                flightAssignment.getEmployee() == null ? null : flightAssignment.getEmployee().getName(),
                flightAssignment.getRequiredSkill(),
                "Employee [%s] on flight assignment [%s] lacks the required skill [%s].".formatted(
                        flightAssignment.getEmployee() == null ? "unassigned" : flightAssignment.getEmployee().getName(),
                        flightAssignment.getId(),
                        flightAssignment.getRequiredSkill()));
    }
}
