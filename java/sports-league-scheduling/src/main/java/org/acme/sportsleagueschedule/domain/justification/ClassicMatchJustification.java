package org.acme.sportsleagueschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.sportsleagueschedule.domain.Match;

public record ClassicMatchJustification(String matchId, String homeTeamId, String awayTeamId, int roundIndex,
        String description) implements ConstraintJustification {

    public ClassicMatchJustification {
        Objects.requireNonNull(matchId);
        Objects.requireNonNull(homeTeamId);
        Objects.requireNonNull(awayTeamId);
        Objects.requireNonNull(description);
    }

    public ClassicMatchJustification(Match match) {
        this(match.getId(), match.getHomeTeam().getId(), match.getAwayTeam().getId(), match.getRoundIndex(),
                "Classic match [%s] between %s and %s is scheduled on round %d, which is not a weekend or holiday."
                        .formatted(match.getId(), match.getHomeTeam().getId(), match.getAwayTeam().getId(),
                                match.getRoundIndex()));
    }
}
