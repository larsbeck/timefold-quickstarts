package org.acme.sportsleagueschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.sportsleagueschedule.domain.Match;

public record RepeatMatchJustification(String matchId, String homeTeamId, String awayTeamId, int roundIndex,
        String description) implements ConstraintJustification {

    public RepeatMatchJustification {
        Objects.requireNonNull(matchId);
        Objects.requireNonNull(homeTeamId);
        Objects.requireNonNull(awayTeamId);
        Objects.requireNonNull(description);
    }

    public RepeatMatchJustification(Match match) {
        this(match.getId(), match.getHomeTeam().getId(), match.getAwayTeam().getId(), match.getRoundIndex(),
                "Match [%s] between %s and %s on round %d has its reverse fixture played on the next day."
                        .formatted(match.getId(), match.getHomeTeam().getId(), match.getAwayTeam().getId(),
                                match.getRoundIndex()));
    }
}
