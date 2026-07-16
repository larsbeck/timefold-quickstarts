package org.acme.tournamentschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.tournamentschedule.domain.TeamAssignment;

public record OneAssignmentPerDatePerTeamJustification(String teamId, int dateIndex, String firstAssignmentId,
        String secondAssignmentId, String description) implements ConstraintJustification {

    public OneAssignmentPerDatePerTeamJustification {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(firstAssignmentId);
        Objects.requireNonNull(secondAssignmentId);
        Objects.requireNonNull(description);
    }

    public OneAssignmentPerDatePerTeamJustification(TeamAssignment assignment, TeamAssignment otherAssignment) {
        this(assignment.getTeam().getId(), assignment.getDay().getDateIndex(), assignment.getId(),
                otherAssignment.getId(),
                "Team [%s] is assigned twice on day [%d]: assignments [%s, %s].".formatted(
                        assignment.getTeam().getId(), assignment.getDay().getDateIndex(), assignment.getId(),
                        otherAssignment.getId()));
    }
}
