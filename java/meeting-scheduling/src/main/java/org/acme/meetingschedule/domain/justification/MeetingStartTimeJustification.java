package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record MeetingStartTimeJustification(String meetingId, int lastTimeGrainIndex,
        String description) implements ConstraintJustification {

    public MeetingStartTimeJustification {
        Objects.requireNonNull(meetingId);
    }

    public MeetingStartTimeJustification(MeetingAssignment meetingAssignment) {
        this(meetingAssignment.getMeeting().getId(), meetingAssignment.getLastTimeGrainIndex(),
                "Meeting %s finishes at time grain index %d; scheduling it earlier is preferred."
                        .formatted(meetingAssignment.getMeeting().getTopic(),
                                meetingAssignment.getLastTimeGrainIndex()));
    }
}
