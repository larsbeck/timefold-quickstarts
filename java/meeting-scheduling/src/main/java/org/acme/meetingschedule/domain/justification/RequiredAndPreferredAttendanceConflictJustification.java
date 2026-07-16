package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;
import org.acme.meetingschedule.domain.PreferredAttendance;
import org.acme.meetingschedule.domain.RequiredAttendance;

public record RequiredAndPreferredAttendanceConflictJustification(String personId, String requiredMeetingId,
        String preferredMeetingId, int overlap, String description) implements ConstraintJustification {

    public RequiredAndPreferredAttendanceConflictJustification {
        Objects.requireNonNull(personId);
        Objects.requireNonNull(requiredMeetingId);
        Objects.requireNonNull(preferredMeetingId);
    }

    public RequiredAndPreferredAttendanceConflictJustification(RequiredAttendance requiredAttendance,
            PreferredAttendance preferredAttendance, MeetingAssignment leftAssignment,
            MeetingAssignment rightAssignment, int overlap) {
        this(requiredAttendance.getPerson().getId(), leftAssignment.getMeeting().getId(),
                rightAssignment.getMeeting().getId(), overlap,
                "Attendee %s has required meeting %s overlapping preferred meeting %s by %d time grain(s)."
                        .formatted(requiredAttendance.getPerson().getFullName(),
                                leftAssignment.getMeeting().getTopic(), rightAssignment.getMeeting().getTopic(),
                                overlap));
    }
}
