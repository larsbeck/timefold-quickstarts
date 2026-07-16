package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Stay;

public record DepartmentSpecialtyJustification(String stayId, String departmentId, String specialty,
        int nightCount, String description) implements ConstraintJustification {

    public DepartmentSpecialtyJustification {
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public DepartmentSpecialtyJustification(Stay stay) {
        this(stay.getId(), stay.getDepartment() == null ? null : stay.getDepartment().getId(), stay.getSpecialty(),
                stay.getNightCount(),
                "Patient [%s] with specialty [%s] stays in department [%s] which does not handle it, for %d night(s)."
                        .formatted(stay.getPatientName(), stay.getSpecialty(),
                                stay.getDepartment() == null ? "?" : stay.getDepartment().getName(),
                                stay.getNightCount()));
    }
}
