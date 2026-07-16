package org.acme.schooltimetabling.domain.justification;

import java.time.Duration;
import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record StudentGroupSubjectVarietyJustification(String lessonId1, String lessonId2, String studentGroup,
        String subject, long gapMinutes, String description) implements ConstraintJustification {

    public StudentGroupSubjectVarietyJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public StudentGroupSubjectVarietyJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(), lesson1.getStudentGroup(), lesson1.getSubject(),
                Duration.between(lesson1.getTimeslot().getEndTime(), lesson2.getTimeslot().getStartTime()).toMinutes(),
                "Student group %s has sequential lessons %s and %s on the same subject %s.".formatted(
                        lesson1.getStudentGroup(), lesson1.getId(), lesson2.getId(), lesson1.getSubject()));
    }
}
