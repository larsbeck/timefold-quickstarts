package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record OvertimeJustification(String meetingId, int lastTimeGrainIndex,
        String description) implements ConstraintJustification {

    public OvertimeJustification {
        Objects.requireNonNull(meetingId);
    }

    public OvertimeJustification(MeetingAssignment meetingAssignment) {
        this(meetingAssignment.getMeeting().getId(), meetingAssignment.getLastTimeGrainIndex(),
                "Meeting %s ends at time grain index %d, which is beyond the available time grains."
                        .formatted(meetingAssignment.getMeeting().getTopic(),
                                meetingAssignment.getLastTimeGrainIndex()));
    }
}
