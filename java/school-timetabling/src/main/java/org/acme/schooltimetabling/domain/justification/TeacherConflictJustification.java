package org.acme.schooltimetabling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record TeacherConflictJustification(String lessonId1, String lessonId2, String teacher, String timeslotId,
        String description) implements ConstraintJustification {

    public TeacherConflictJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public TeacherConflictJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(), lesson1.getTeacher(),
                lesson1.getTimeslot() == null ? null : lesson1.getTimeslot().getId(),
                "Teacher %s is assigned to lessons %s and %s at the same timeslot %s.".formatted(lesson1.getTeacher(),
                        lesson1.getId(), lesson2.getId(), lesson1.getTimeslot()));
    }
}
