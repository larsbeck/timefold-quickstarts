package org.acme.schooltimetabling.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.schooltimetabling.domain.Lesson;

public record RoomConflictJustification(String lessonId1, String lessonId2, String roomId, String timeslotId,
        String description) implements ConstraintJustification {

    public RoomConflictJustification {
        Objects.requireNonNull(lessonId1);
        Objects.requireNonNull(lessonId2);
        Objects.requireNonNull(description);
    }

    public RoomConflictJustification(Lesson lesson1, Lesson lesson2) {
        this(lesson1.getId(), lesson2.getId(),
                lesson1.getRoom() == null ? null : lesson1.getRoom().getId(),
                lesson1.getTimeslot() == null ? null : lesson1.getTimeslot().getId(),
                "Lessons %s and %s share room %s at timeslot %s.".formatted(lesson1.getId(), lesson2.getId(),
                        lesson1.getRoom(), lesson1.getTimeslot()));
    }
}
