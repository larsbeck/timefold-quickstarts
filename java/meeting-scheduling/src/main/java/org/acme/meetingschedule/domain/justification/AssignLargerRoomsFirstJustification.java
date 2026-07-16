package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;
import org.acme.meetingschedule.domain.Room;

public record AssignLargerRoomsFirstJustification(String meetingId, String assignedRoomId, int assignedRoomCapacity,
        String largerRoomId, int largerRoomCapacity, int capacityDifference,
        String description) implements ConstraintJustification {

    public AssignLargerRoomsFirstJustification {
        Objects.requireNonNull(meetingId);
        Objects.requireNonNull(assignedRoomId);
        Objects.requireNonNull(largerRoomId);
    }

    public AssignLargerRoomsFirstJustification(MeetingAssignment meetingAssignment, Room largerRoom) {
        this(meetingAssignment.getMeeting().getId(), meetingAssignment.getRoom().getId(),
                meetingAssignment.getRoomCapacity(), largerRoom.getId(), largerRoom.getCapacity(),
                largerRoom.getCapacity() - meetingAssignment.getRoomCapacity(),
                "Meeting %s uses room %s (capacity %d) while larger room %s (capacity %d) remains available."
                        .formatted(meetingAssignment.getMeeting().getTopic(), meetingAssignment.getRoom().getName(),
                                meetingAssignment.getRoomCapacity(), largerRoom.getName(), largerRoom.getCapacity()));
    }
}
