package org.acme.meetingschedule.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.meetingschedule.domain.MeetingAssignment;
import org.acme.meetingschedule.domain.TimeGrain;

public record StartAndEndOnSameDayJustification(String meetingId, Integer startDayOfYear, Integer endDayOfYear,
        String description) implements ConstraintJustification {

    public StartAndEndOnSameDayJustification {
        Objects.requireNonNull(meetingId);
    }

    public StartAndEndOnSameDayJustification(MeetingAssignment meetingAssignment, TimeGrain endTimeGrain) {
        this(meetingAssignment.getMeeting().getId(),
                meetingAssignment.getStartingTimeGrain().getDayOfYear(), endTimeGrain.getDayOfYear(),
                "Meeting %s starts on day %d but ends on day %d."
                        .formatted(meetingAssignment.getMeeting().getTopic(),
                                meetingAssignment.getStartingTimeGrain().getDayOfYear(), endTimeGrain.getDayOfYear()));
    }
}
