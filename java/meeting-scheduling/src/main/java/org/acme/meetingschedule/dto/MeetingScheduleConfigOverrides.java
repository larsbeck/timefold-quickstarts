package org.acme.meetingschedule.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.meetingschedule.solver.MeetingScheduleConstraintProperties;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Definition of soft constraint weights. Every constraint has a default weight of 1, meaning that all constraints are equally important. "
        + "Use this to express preference of some constraints over others. "
        + "In order to turn off a constraint, set the value of the corresponding attribute to 0.")
public record MeetingScheduleConfigOverrides(
        @ConstraintReference(MeetingScheduleConstraintProperties.DO_ALL_MEETINGS_AS_SOON_AS_POSSIBLE) @Schema(
                description = "Soft weight of the do meetings as soon as possible constraint.") long doMeetingsAsSoonAsPossibleWeight,
        @ConstraintReference(MeetingScheduleConstraintProperties.ONE_TIME_GRAIN_BREAK_BETWEEN_TWO_CONSECUTIVE_MEETINGS) @Schema(
                description = "Soft weight of the one break between consecutive meetings constraint.") long oneBreakBetweenConsecutiveMeetingsWeight,
        @ConstraintReference(MeetingScheduleConstraintProperties.OVERLAPPING_MEETINGS) @Schema(
                description = "Soft weight of the overlapping meetings constraint.") long overlappingMeetingsWeight,
        @ConstraintReference(MeetingScheduleConstraintProperties.ASSIGN_LARGER_ROOMS_FIRST) @Schema(
                description = "Soft weight of the assign larger rooms first constraint.") long assignLargerRoomsFirstWeight,
        @ConstraintReference(MeetingScheduleConstraintProperties.ROOM_STABILITY) @Schema(
                description = "Soft weight of the room stability constraint.") long roomStabilityWeight)
        implements
            ModelConfigOverrides {

    public MeetingScheduleConfigOverrides {
        doMeetingsAsSoonAsPossibleWeight = Math.max(0L, doMeetingsAsSoonAsPossibleWeight);
        oneBreakBetweenConsecutiveMeetingsWeight = Math.max(0L, oneBreakBetweenConsecutiveMeetingsWeight);
        overlappingMeetingsWeight = Math.max(0L, overlappingMeetingsWeight);
        assignLargerRoomsFirstWeight = Math.max(0L, assignLargerRoomsFirstWeight);
        roomStabilityWeight = Math.max(0L, roomStabilityWeight);
    }

    public MeetingScheduleConfigOverrides() {
        this(1L, 1L, 1L, 1L, 1L);
    }

    public MeetingScheduleConfigOverrides withDoMeetingsAsSoonAsPossibleWeight(long doMeetingsAsSoonAsPossibleWeight) {
        return new MeetingScheduleConfigOverrides(doMeetingsAsSoonAsPossibleWeight, oneBreakBetweenConsecutiveMeetingsWeight,
                overlappingMeetingsWeight, assignLargerRoomsFirstWeight, roomStabilityWeight);
    }

    public MeetingScheduleConfigOverrides withOneBreakBetweenConsecutiveMeetingsWeight(
            long oneBreakBetweenConsecutiveMeetingsWeight) {
        return new MeetingScheduleConfigOverrides(doMeetingsAsSoonAsPossibleWeight, oneBreakBetweenConsecutiveMeetingsWeight,
                overlappingMeetingsWeight, assignLargerRoomsFirstWeight, roomStabilityWeight);
    }

    public MeetingScheduleConfigOverrides withOverlappingMeetingsWeight(long overlappingMeetingsWeight) {
        return new MeetingScheduleConfigOverrides(doMeetingsAsSoonAsPossibleWeight, oneBreakBetweenConsecutiveMeetingsWeight,
                overlappingMeetingsWeight, assignLargerRoomsFirstWeight, roomStabilityWeight);
    }

    public MeetingScheduleConfigOverrides withAssignLargerRoomsFirstWeight(long assignLargerRoomsFirstWeight) {
        return new MeetingScheduleConfigOverrides(doMeetingsAsSoonAsPossibleWeight, oneBreakBetweenConsecutiveMeetingsWeight,
                overlappingMeetingsWeight, assignLargerRoomsFirstWeight, roomStabilityWeight);
    }

    public MeetingScheduleConfigOverrides withRoomStabilityWeight(long roomStabilityWeight) {
        return new MeetingScheduleConfigOverrides(doMeetingsAsSoonAsPossibleWeight, oneBreakBetweenConsecutiveMeetingsWeight,
                overlappingMeetingsWeight, assignLargerRoomsFirstWeight, roomStabilityWeight);
    }
}
