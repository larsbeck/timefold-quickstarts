package org.acme.sportsleagueschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.sportsleagueschedule.domain.Match;

public record TravelHopJustification(String hopType, String matchId, String nextMatchId, int distance,
        String description) implements ConstraintJustification {

    public TravelHopJustification {
        Objects.requireNonNull(hopType);
        Objects.requireNonNull(matchId);
        Objects.requireNonNull(description);
    }

    public TravelHopJustification(String hopType, Match match, int distance) {
        this(hopType, match.getId(), null, distance,
                "%s for match [%s] adds a travel distance of %d."
                        .formatted(hopType, match.getId(), distance));
    }

    public TravelHopJustification(String hopType, Match match, Match nextMatch, int distance) {
        this(hopType, match.getId(), nextMatch.getId(), distance,
                "%s from match [%s] to match [%s] adds a travel distance of %d."
                        .formatted(hopType, match.getId(), nextMatch.getId(), distance));
    }
}
