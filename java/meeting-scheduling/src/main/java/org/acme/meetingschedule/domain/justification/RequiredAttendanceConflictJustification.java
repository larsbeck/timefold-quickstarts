package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;
import org.acme.meetingschedule.domain.RequiredAttendance;

public record RequiredAttendanceConflictJustification(String personId, String leftMeetingId, String rightMeetingId,
        int overlap, String description) implements ConstraintJustification {

    public RequiredAttendanceConflictJustification {
        Objects.requireNonNull(personId);
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public RequiredAttendanceConflictJustification(RequiredAttendance leftRequiredAttendance,
            RequiredAttendance rightRequiredAttendance, MeetingAssignment leftAssignment,
            MeetingAssignment rightAssignment, int overlap) {
        this(leftRequiredAttendance.getPerson().getId(), leftAssignment.getMeeting().getId(),
                rightAssignment.getMeeting().getId(), overlap,
                "Required attendee %s is expected at overlapping meetings %s and %s (overlap of %d time grain(s))."
                        .formatted(leftRequiredAttendance.getPerson().getFullName(),
                                leftAssignment.getMeeting().getTopic(), rightAssignment.getMeeting().getTopic(),
                                overlap));
    }
}
