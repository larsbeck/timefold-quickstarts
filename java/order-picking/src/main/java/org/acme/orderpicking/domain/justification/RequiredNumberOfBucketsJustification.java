package org.acme.orderpicking.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.orderpicking.domain.Trolley;

public record RequiredNumberOfBucketsJustification(String trolleyId, long requiredBuckets, int availableBuckets,
        long overCapacityBy, String description) implements ConstraintJustification {

    public RequiredNumberOfBucketsJustification {
        Objects.requireNonNull(trolleyId);
        Objects.requireNonNull(description);
    }

    public RequiredNumberOfBucketsJustification(Trolley trolley, long requiredBuckets) {
        this(trolley.getId(), requiredBuckets, trolley.getBucketCount(), requiredBuckets - trolley.getBucketCount(),
                "Trolley %s requires %d buckets but only has %d, over capacity by %d.".formatted(trolley.getId(),
                        requiredBuckets, trolley.getBucketCount(), requiredBuckets - trolley.getBucketCount()));
    }
}
