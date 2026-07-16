package org.acme.tournamentschedule.domain.justification;

import java.math.BigDecimal;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;
import ai.timefold.solver.core.api.score.stream.common.LoadBalance;

public record EvenlyConfrontationCountJustification(BigDecimal unfairness, String description)
        implements
            ConstraintJustification {

    public EvenlyConfrontationCountJustification {
        Objects.requireNonNull(unfairness);
        Objects.requireNonNull(description);
    }

    public EvenlyConfrontationCountJustification(LoadBalance<?> loadBalance) {
        this(loadBalance.unfairness(),
                "Confrontation counts across team pairs are unbalanced with unfairness [%s].".formatted(
                        loadBalance.unfairness()));
    }
}
