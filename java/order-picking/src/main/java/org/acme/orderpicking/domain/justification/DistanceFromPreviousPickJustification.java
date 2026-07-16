package org.acme.orderpicking.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.orderpicking.domain.PickTask;

public record DistanceFromPreviousPickJustification(String pickTaskId, String trolleyId, int distance,
        String description) implements ConstraintJustification {

    public DistanceFromPreviousPickJustification {
        Objects.requireNonNull(pickTaskId);
        Objects.requireNonNull(description);
    }

    public DistanceFromPreviousPickJustification(PickTask pick, int distance) {
        this(pick.getId(), pick.getTrolleyId(), distance,
                "Pick %s on trolley %s is %d away from the previous pick.".formatted(pick.getId(), pick.getTrolleyId(),
                        distance));
    }
}
