package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record DepartmentSpecialtyNotFirstPriorityJustification(String stayId, String departmentId, String specialty,
        int specialtyPriority, int nightCount, String description) implements ConstraintJustification {

    public DepartmentSpecialtyNotFirstPriorityJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public DepartmentSpecialtyNotFirstPriorityJustification(Stay stay) {
        this(stay.getId(), stay.getDepartment() == null ? null : stay.getDepartment().getId(), stay.getSpecialty(),
                stay.getSpecialtyPriority(), stay.getNightCount(),
                "Patient [%s] with specialty [%s] is in department [%s] at priority %d (not first) for %d night(s)."
                        .formatted(stay.getPatientName(), stay.getSpecialty(),
                                stay.getDepartment() == null ? "?" : stay.getDepartment().getName(),
                                stay.getSpecialtyPriority(), stay.getNightCount()));
    }
}
