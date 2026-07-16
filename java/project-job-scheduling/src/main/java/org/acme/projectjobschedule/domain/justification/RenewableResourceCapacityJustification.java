package org.acme.projectjobschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.projectjobschedule.domain.resource.Resource;

public record RenewableResourceCapacityJustification(String resourceId, int date, int capacity, long totalRequirement,
        long overCapacityBy, String description) implements ConstraintJustification {

    public RenewableResourceCapacityJustification {
        Objects.requireNonNull(resourceId);
    }

    public RenewableResourceCapacityJustification(Resource resource, int date, long totalRequirement) {
        this(resource.getId(), date, resource.getCapacity(), totalRequirement,
                totalRequirement - resource.getCapacity(),
                "Renewable resource [%s] is used %d beyond its per-day capacity of %d on day %d (total requirement %d)."
                        .formatted(resource.getId(), totalRequirement - resource.getCapacity(), resource.getCapacity(),
                                date, totalRequirement));
    }
}
