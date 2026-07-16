package org.acme.schooltimetabling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record StudentGroupConflictJustification(String lessonId1, String lessonId2, String studentGroup,
        String timeslotId, String description) implements ConstraintJustification {

    public StudentGroupConflictJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public StudentGroupConflictJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(), lesson1.getStudentGroup(),
                lesson1.getTimeslot() == null ? null : lesson1.getTimeslot().getId(),
                "Student group %s must attend lessons %s and %s at the same timeslot %s.".formatted(
                        lesson1.getStudentGroup(), lesson1.getId(), lesson2.getId(), lesson1.getTimeslot()));
    }
}
