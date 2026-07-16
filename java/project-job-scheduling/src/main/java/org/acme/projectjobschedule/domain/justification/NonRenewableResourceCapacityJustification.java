package org.acme.projectjobschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.projectjobschedule.domain.resource.Resource;

public record NonRenewableResourceCapacityJustification(String resourceId, int capacity, long totalRequirement,
        long overCapacityBy, String description) implements ConstraintJustification {

    public NonRenewableResourceCapacityJustification {
        Objects.requireNonNull(resourceId);
    }

    public NonRenewableResourceCapacityJustification(Resource resource, long totalRequirement) {
        this(resource.getId(), resource.getCapacity(), totalRequirement, totalRequirement - resource.getCapacity(),
                "Non-renewable resource [%s] is used %d beyond its total capacity of %d (total requirement %d)."
                        .formatted(resource.getId(), totalRequirement - resource.getCapacity(), resource.getCapacity(),
                                totalRequirement));
    }
}
