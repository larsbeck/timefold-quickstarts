package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;

public record RequiredRoomCapacityJustification(String meetingId, int requiredCapacity, int roomCapacity,
        int overCapacityBy, String description) implements ConstraintJustification {

    public RequiredRoomCapacityJustification {
        Objects.requireNonNull(meetingId);
    }

    public RequiredRoomCapacityJustification(MeetingAssignment meetingAssignment) {
        this(meetingAssignment.getMeeting().getId(), meetingAssignment.getRequiredCapacity(),
                meetingAssignment.getRoomCapacity(),
                meetingAssignment.getRequiredCapacity() - meetingAssignment.getRoomCapacity(),
                "Meeting %s needs capacity %d but the assigned room only holds %d (short by %d)."
                        .formatted(meetingAssignment.getMeeting().getTopic(), meetingAssignment.getRequiredCapacity(),
                                meetingAssignment.getRoomCapacity(),
                                meetingAssignment.getRequiredCapacity() - meetingAssignment.getRoomCapacity()));
    }
}
