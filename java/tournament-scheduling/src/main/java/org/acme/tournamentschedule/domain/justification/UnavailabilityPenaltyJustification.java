package org.acme.tournamentschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.tournamentschedule.domain.UnavailabilityPenalty;

public record UnavailabilityPenaltyJustification(String teamId, int dateIndex, String description)
        implements
            ConstraintJustification {

    public UnavailabilityPenaltyJustification {
        Objects.requireNonNull(teamId);
        Objects.requireNonNull(description);
    }

    public UnavailabilityPenaltyJustification(UnavailabilityPenalty penalty) {
        this(penalty.getTeam().getId(), penalty.getDay().getDateIndex(),
                "Team [%s] is assigned on day [%d] on which it is unavailable.".formatted(penalty.getTeam().getId(),
                        penalty.getDay().getDateIndex()));
    }
}
