package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;
import org.acme.meetingschedule.domain.PreferredAttendance;

public record PreferredAttendanceConflictJustification(String personId, String leftMeetingId, String rightMeetingId,
        int overlap, String description) implements ConstraintJustification {

    public PreferredAttendanceConflictJustification {
        Objects.requireNonNull(personId);
        Objects.requireNonNull(leftMeetingId);
        Objects.requireNonNull(rightMeetingId);
    }

    public PreferredAttendanceConflictJustification(PreferredAttendance leftPreferredAttendance,
            PreferredAttendance rightPreferredAttendance, MeetingAssignment leftAssignment,
            MeetingAssignment rightAssignment, int overlap) {
        this(leftPreferredAttendance.getPerson().getId(), leftAssignment.getMeeting().getId(),
                rightAssignment.getMeeting().getId(), overlap,
                "Preferred attendee %s is expected at overlapping meetings %s and %s (overlap of %d time grain(s))."
                        .formatted(leftPreferredAttendance.getPerson().getFullName(),
                                leftAssignment.getMeeting().getTopic(), rightAssignment.getMeeting().getTopic(),
                                overlap));
    }
}
