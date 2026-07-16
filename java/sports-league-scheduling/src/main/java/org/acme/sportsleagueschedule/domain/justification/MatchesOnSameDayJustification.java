package org.acme.sportsleagueschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.sportsleagueschedule.domain.Match;

public record MatchesOnSameDayJustification(String matchId1, String matchId2, int roundIndex, String description)
        implements
            ConstraintJustification {

    public MatchesOnSameDayJustification {
        Objects.requireNonNull(matchId1);
        Objects.requireNonNull(matchId2);
        Objects.requireNonNull(description);
    }

    public MatchesOnSameDayJustification(Match match1, Match match2) {
        this(match1.getId(), match2.getId(), match1.getRoundIndex(),
                "Matches [%s] and [%s] share a team but are both scheduled on round %d."
                        .formatted(match1.getId(), match2.getId(), match1.getRoundIndex()));
    }
}
