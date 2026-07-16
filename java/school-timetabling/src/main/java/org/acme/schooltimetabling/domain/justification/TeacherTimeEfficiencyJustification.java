package org.acme.schooltimetabling.domain.justification;

import java.time.Duration;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record TeacherTimeEfficiencyJustification(String lessonId1, String lessonId2, String teacher,
        long gapMinutes, String description) implements ConstraintJustification {

    public TeacherTimeEfficiencyJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public TeacherTimeEfficiencyJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(), lesson1.getTeacher(),
                Duration.between(lesson1.getTimeslot().getEndTime(), lesson2.getTimeslot().getStartTime()).toMinutes(),
                "Teacher %s teaches sequential lessons %s and %s with a %d minute gap.".formatted(lesson1.getTeacher(),
                        lesson1.getId(), lesson2.getId(),
                        Duration.between(lesson1.getTimeslot().getEndTime(), lesson2.getTimeslot().getStartTime())
                                .toMinutes()));
    }
}
