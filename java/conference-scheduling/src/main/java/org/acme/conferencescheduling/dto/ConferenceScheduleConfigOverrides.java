package org.acme.conferencescheduling.dto;

import ai.timefold.solver.service.definition.api.ModelConfigOverrides;
import ai.timefold.solver.service.definition.api.domain.ConstraintReference;

import org.acme.conferencescheduling.domain.ConferenceConstraintProperties;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Soft constraint weights. Every constraint has a default weight of 1. "
        + "Set a weight to 0 to disable the corresponding constraint.")
public record ConferenceScheduleConfigOverrides(
        @ConstraintReference(ConferenceConstraintProperties.THEME_TRACK_CONFLICT) @Schema(
                description = "Soft weight of the themeTrackConflict constraint.") long themeTrackConflictWeight,
        @ConstraintReference(ConferenceConstraintProperties.THEME_TRACK_ROOM_STABILITY) @Schema(
                description = "Soft weight of the themeTrackRoomStability constraint.") long themeTrackRoomStabilityWeight,
        @ConstraintReference(ConferenceConstraintProperties.SECTOR_CONFLICT) @Schema(
                description = "Soft weight of the sectorConflict constraint.") long sectorConflictWeight,
        @ConstraintReference(ConferenceConstraintProperties.AUDIENCE_TYPE_DIVERSITY) @Schema(
                description = "Soft weight of the audienceTypeDiversity constraint.") long audienceTypeDiversityWeight,
        @ConstraintReference(ConferenceConstraintProperties.AUDIENCE_TYPE_THEME_TRACK_CONFLICT) @Schema(
                description = "Soft weight of the audienceTypeThemeTrackConflict constraint.") long audienceTypeThemeTrackConflictWeight,
        @ConstraintReference(ConferenceConstraintProperties.AUDIENCE_LEVEL_DIVERSITY) @Schema(
                description = "Soft weight of the audienceLevelDiversity constraint.") long audienceLevelDiversityWeight,
        @ConstraintReference(ConferenceConstraintProperties.CONTENT_AUDIENCE_LEVEL_FLOW_VIOLATION) @Schema(
                description = "Soft weight of the contentAudienceLevelFlowViolation constraint.") long contentAudienceLevelFlowViolationWeight,
        @ConstraintReference(ConferenceConstraintProperties.CONTENT_CONFLICT) @Schema(
                description = "Soft weight of the contentConflict constraint.") long contentConflictWeight,
        @ConstraintReference(ConferenceConstraintProperties.LANGUAGE_DIVERSITY) @Schema(
                description = "Soft weight of the languageDiversity constraint.") long languageDiversityWeight,
        @ConstraintReference(ConferenceConstraintProperties.SAME_DAY_TALKS) @Schema(
                description = "Soft weight of the sameDayTalks constraint.") long sameDayTalksWeight,
        @ConstraintReference(ConferenceConstraintProperties.POPULAR_TALKS) @Schema(
                description = "Soft weight of the popularTalks constraint.") long popularTalksWeight,
        @ConstraintReference(ConferenceConstraintProperties.SPEAKER_PREFERRED_TIMESLOT_TAGS) @Schema(
                description = "Soft weight of the speakerPreferredTimeslotTags constraint.") long speakerPreferredTimeslotTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.SPEAKER_UNDESIRED_TIMESLOT_TAGS) @Schema(
                description = "Soft weight of the speakerUndesiredTimeslotTags constraint.") long speakerUndesiredTimeslotTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.TALK_PREFERRED_TIMESLOT_TAGS) @Schema(
                description = "Soft weight of the talkPreferredTimeslotTags constraint.") long talkPreferredTimeslotTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.TALK_UNDESIRED_TIMESLOT_TAGS) @Schema(
                description = "Soft weight of the talkUndesiredTimeslotTags constraint.") long talkUndesiredTimeslotTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.SPEAKER_PREFERRED_ROOM_TAGS) @Schema(
                description = "Soft weight of the speakerPreferredRoomTags constraint.") long speakerPreferredRoomTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.SPEAKER_UNDESIRED_ROOM_TAGS) @Schema(
                description = "Soft weight of the speakerUndesiredRoomTags constraint.") long speakerUndesiredRoomTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.TALK_PREFERRED_ROOM_TAGS) @Schema(
                description = "Soft weight of the talkPreferredRoomTags constraint.") long talkPreferredRoomTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.TALK_UNDESIRED_ROOM_TAGS) @Schema(
                description = "Soft weight of the talkUndesiredRoomTags constraint.") long talkUndesiredRoomTagsWeight,
        @ConstraintReference(ConferenceConstraintProperties.SPEAKER_MAKESPAN) @Schema(
                description = "Soft weight of the speakerMakespan constraint.") long speakerMakespanWeight)
        implements
            ModelConfigOverrides {

    public ConferenceScheduleConfigOverrides {
        themeTrackConflictWeight = Math.max(0L, themeTrackConflictWeight);
        themeTrackRoomStabilityWeight = Math.max(0L, themeTrackRoomStabilityWeight);
        sectorConflictWeight = Math.max(0L, sectorConflictWeight);
        audienceTypeDiversityWeight = Math.max(0L, audienceTypeDiversityWeight);
        audienceTypeThemeTrackConflictWeight = Math.max(0L, audienceTypeThemeTrackConflictWeight);
        audienceLevelDiversityWeight = Math.max(0L, audienceLevelDiversityWeight);
        contentAudienceLevelFlowViolationWeight = Math.max(0L, contentAudienceLevelFlowViolationWeight);
        contentConflictWeight = Math.max(0L, contentConflictWeight);
        languageDiversityWeight = Math.max(0L, languageDiversityWeight);
        sameDayTalksWeight = Math.max(0L, sameDayTalksWeight);
        popularTalksWeight = Math.max(0L, popularTalksWeight);
        speakerPreferredTimeslotTagsWeight = Math.max(0L, speakerPreferredTimeslotTagsWeight);
        speakerUndesiredTimeslotTagsWeight = Math.max(0L, speakerUndesiredTimeslotTagsWeight);
        talkPreferredTimeslotTagsWeight = Math.max(0L, talkPreferredTimeslotTagsWeight);
        talkUndesiredTimeslotTagsWeight = Math.max(0L, talkUndesiredTimeslotTagsWeight);
        speakerPreferredRoomTagsWeight = Math.max(0L, speakerPreferredRoomTagsWeight);
        speakerUndesiredRoomTagsWeight = Math.max(0L, speakerUndesiredRoomTagsWeight);
        talkPreferredRoomTagsWeight = Math.max(0L, talkPreferredRoomTagsWeight);
        talkUndesiredRoomTagsWeight = Math.max(0L, talkUndesiredRoomTagsWeight);
        speakerMakespanWeight = Math.max(0L, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides() {
        this(1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L);
    }

    public ConferenceScheduleConfigOverrides withThemeTrackConflictWeight(long themeTrackConflictWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withThemeTrackRoomStabilityWeight(long themeTrackRoomStabilityWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSectorConflictWeight(long sectorConflictWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withAudienceTypeDiversityWeight(long audienceTypeDiversityWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides
            withAudienceTypeThemeTrackConflictWeight(long audienceTypeThemeTrackConflictWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withAudienceLevelDiversityWeight(long audienceLevelDiversityWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides
            withContentAudienceLevelFlowViolationWeight(long contentAudienceLevelFlowViolationWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withContentConflictWeight(long contentConflictWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withLanguageDiversityWeight(long languageDiversityWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSameDayTalksWeight(long sameDayTalksWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withPopularTalksWeight(long popularTalksWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSpeakerPreferredTimeslotTagsWeight(long speakerPreferredTimeslotTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSpeakerUndesiredTimeslotTagsWeight(long speakerUndesiredTimeslotTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withTalkPreferredTimeslotTagsWeight(long talkPreferredTimeslotTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withTalkUndesiredTimeslotTagsWeight(long talkUndesiredTimeslotTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSpeakerPreferredRoomTagsWeight(long speakerPreferredRoomTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSpeakerUndesiredRoomTagsWeight(long speakerUndesiredRoomTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withTalkPreferredRoomTagsWeight(long talkPreferredRoomTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withTalkUndesiredRoomTagsWeight(long talkUndesiredRoomTagsWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }

    public ConferenceScheduleConfigOverrides withSpeakerMakespanWeight(long speakerMakespanWeight) {
        return new ConferenceScheduleConfigOverrides(themeTrackConflictWeight, themeTrackRoomStabilityWeight,
                sectorConflictWeight, audienceTypeDiversityWeight, audienceTypeThemeTrackConflictWeight,
                audienceLevelDiversityWeight, contentAudienceLevelFlowViolationWeight, contentConflictWeight,
                languageDiversityWeight, sameDayTalksWeight, popularTalksWeight, speakerPreferredTimeslotTagsWeight,
                speakerUndesiredTimeslotTagsWeight, talkPreferredTimeslotTagsWeight, talkUndesiredTimeslotTagsWeight,
                speakerPreferredRoomTagsWeight, speakerUndesiredRoomTagsWeight, talkPreferredRoomTagsWeight,
                talkUndesiredRoomTagsWeight, speakerMakespanWeight);
    }
}
