package org.acme.tournamentschedule.domain.justification;

import java.math.BigDecimal;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import ai.timefold.solver.core.api.score.stream.common.LoadBalance;

public record FairAssignmentCountPerTeamJustification(BigDecimal unfairness, String description)
        implements
            ConstraintJustification {

    public FairAssignmentCountPerTeamJustification {
        Objects.requireNonNull(unfairness);
        Objects.requireNonNull(description);
    }

    public FairAssignmentCountPerTeamJustification(LoadBalance<?> loadBalance) {
        this(loadBalance.unfairness(),
                "Assignment counts across teams are unbalanced with unfairness [%s].".formatted(
                        loadBalance.unfairness()));
    }
}
