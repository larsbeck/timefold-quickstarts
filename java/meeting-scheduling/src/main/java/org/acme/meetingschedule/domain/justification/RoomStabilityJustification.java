package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.Attendance;
import org.acme.meetingschedule.domain.MeetingAssignment;

public record RoomStabilityJustification(String personId, String leftMeetingId, String leftRoomId,
        String rightMeetingId, String rightRoomId, String description) implements ConstraintJustification {

    public RoomStabilityJustification {
        Objects.requireNonNull(personId);
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public RoomStabilityJustification(Attendance leftAttendance, Attendance rightAttendance,
            MeetingAssignment leftAssignment, MeetingAssignment rightAssignment) {
        this(leftAttendance.getPerson().getId(), leftAssignment.getMeeting().getId(),
                leftAssignment.getRoom().getId(), rightAssignment.getMeeting().getId(),
                rightAssignment.getRoom().getId(),
                "Attendee %s moves from room %s (meeting %s) to room %s (meeting %s) for consecutive meetings."
                        .formatted(leftAttendance.getPerson().getFullName(), leftAssignment.getRoom().getName(),
                                leftAssignment.getMeeting().getTopic(), rightAssignment.getRoom().getName(),
                                rightAssignment.getMeeting().getTopic()));
    }
}
