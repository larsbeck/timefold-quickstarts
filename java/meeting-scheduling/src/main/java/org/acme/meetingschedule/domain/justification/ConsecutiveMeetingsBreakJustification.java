package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record ConsecutiveMeetingsBreakJustification(String leftMeetingId, String rightMeetingId,
        String description) implements ConstraintJustification {

    public ConsecutiveMeetingsBreakJustification {
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public ConsecutiveMeetingsBreakJustification(MeetingAssignment leftAssignment, MeetingAssignment rightAssignment) {
        this(leftAssignment.getMeeting().getId(), rightAssignment.getMeeting().getId(),
                "Meeting %s ends immediately before meeting %s starts, leaving no break in between."
                        .formatted(leftAssignment.getMeeting().getTopic(), rightAssignment.getMeeting().getTopic()));
    }
}
