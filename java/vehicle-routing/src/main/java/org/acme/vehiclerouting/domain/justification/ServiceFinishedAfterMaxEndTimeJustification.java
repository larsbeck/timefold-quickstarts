package org.acme.vehiclerouting.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

public record ServiceFinishedAfterMaxEndTimeJustification(String visitId, long delayInMinutes,
        String description) implements ConstraintJustification {

    public ServiceFinishedAfterMaxEndTimeJustification {
        Objects.requireNonNull(visitId);
    }

    public ServiceFinishedAfterMaxEndTimeJustification(String visitId, long delayInMinutes) {
        this(visitId, delayInMinutes,
                "Visit %s finishes service %d minute(s) after its time window closes."
                        .formatted(visitId, delayInMinutes));
    }
}
