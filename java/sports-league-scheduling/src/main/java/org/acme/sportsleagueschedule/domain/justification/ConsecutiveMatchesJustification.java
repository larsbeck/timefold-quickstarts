package org.acme.sportsleagueschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.sportsleagueschedule.domain.Team;

public record ConsecutiveMatchesJustification(String teamId, String matchType, int consecutiveMatches,
        String description) implements ConstraintJustification {

    public ConsecutiveMatchesJustification {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(matchType);
        Objects.requireNonNull(description);
    }

    public ConsecutiveMatchesJustification(Team team, String matchType, int consecutiveMatches) {
        this(team.getId(), matchType, consecutiveMatches,
                "Team [%s] plays %d consecutive %s matches."
                        .formatted(team.getId(), consecutiveMatches, matchType));
    }
}
