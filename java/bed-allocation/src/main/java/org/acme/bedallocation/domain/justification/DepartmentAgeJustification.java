package org.acme.bedallocation.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.bedallocation.domain.Department;
import org.acme.bedallocation.domain.Stay;

public record DepartmentAgeJustification(String departmentId, String stayId, int patientAge, int ageLimit,
        int nightCount, String description) implements ConstraintJustification {

    public DepartmentAgeJustification {
        Objects.requireNonNull(departmentId);
        Objects.requireNonNull(stayId);
        Objects.requireNonNull(description);
    }

    public DepartmentAgeJustification(String boundType, Department department, Stay stay, int ageLimit) {
        this(department.getId(), stay.getId(), stay.getPatientAge(), ageLimit, stay.getNightCount(),
                "Patient [%s] aged %d violates the %s age [%d] of department [%s] for %d night(s)."
                        .formatted(stay.getPatientName(), stay.getPatientAge(), boundType, ageLimit,
                                department.getName(), stay.getNightCount()));
    }
}
