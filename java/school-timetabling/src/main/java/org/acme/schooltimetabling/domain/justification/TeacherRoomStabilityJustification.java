package org.acme.schooltimetabling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record TeacherRoomStabilityJustification(String lessonId1, String lessonId2, String teacher, String roomId1,
        String roomId2, String description) implements ConstraintJustification {

    public TeacherRoomStabilityJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public TeacherRoomStabilityJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(), lesson1.getTeacher(),
                lesson1.getRoom() == null ? null : lesson1.getRoom().getId(),
                lesson2.getRoom() == null ? null : lesson2.getRoom().getId(),
                "Teacher %s teaches lessons %s and %s in different rooms %s and %s.".formatted(lesson1.getTeacher(),
                        lesson1.getId(), lesson2.getId(), lesson1.getRoom(), lesson2.getRoom()));
    }
}
