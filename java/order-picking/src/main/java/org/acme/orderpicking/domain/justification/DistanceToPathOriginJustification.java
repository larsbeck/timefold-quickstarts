package org.acme.orderpicking.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.orderpicking.domain.PickTask;

public record DistanceToPathOriginJustification(String pickTaskId, String trolleyId, int distance,
        String description) implements ConstraintJustification {

    public DistanceToPathOriginJustification {
        Objects.requireNonNull(pickTaskId);
        Objects.requireNonNull(description);
    }

    public DistanceToPathOriginJustification(PickTask pick, int distance) {
        this(pick.getId(), pick.getTrolleyId(), distance,
                "Last pick %s on trolley %s is %d away from the path origin.".formatted(pick.getId(),
                        pick.getTrolleyId(), distance));
    }
}
