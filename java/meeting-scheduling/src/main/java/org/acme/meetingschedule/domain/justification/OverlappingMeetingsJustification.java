package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record OverlappingMeetingsJustification(String leftMeetingId, String rightMeetingId, int overlap,
        String description) implements ConstraintJustification {

    public OverlappingMeetingsJustification {
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public OverlappingMeetingsJustification(MeetingAssignment leftAssignment, MeetingAssignment rightAssignment,
            int overlap) {
        this(leftAssignment.getMeeting().getId(), rightAssignment.getMeeting().getId(), overlap,
                "Meetings %s and %s overlap by %d time grain(s)."
                        .formatted(leftAssignment.getMeeting().getTopic(), rightAssignment.getMeeting().getTopic(),
                                overlap));
    }
}
