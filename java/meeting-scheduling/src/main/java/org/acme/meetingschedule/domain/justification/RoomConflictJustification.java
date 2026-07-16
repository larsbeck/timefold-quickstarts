package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record RoomConflictJustification(String roomId, String leftMeetingId, String rightMeetingId, int overlap,
        String description) implements ConstraintJustification {

    public RoomConflictJustification {
        Objects.requireNonNull(roomId);
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public RoomConflictJustification(MeetingAssignment leftAssignment, MeetingAssignment rightAssignment, int overlap) {
        this(leftAssignment.getRoom().getId(), leftAssignment.getMeeting().getId(), rightAssignment.getMeeting().getId(),
                overlap,
                "Room %s hosts meetings %s and %s that overlap by %d time grain(s)."
                        .formatted(leftAssignment.getRoom().getName(), leftAssignment.getMeeting().getTopic(),
                                rightAssignment.getMeeting().getTopic(), overlap));
    }
}
