package org.acme.conferencescheduling.service;

import static java.util.stream.Collectors.toCollection;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SequencedSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import ai.timefold.solver.core.api.domain.solution.ConstraintWeightOverrides;
import ai.timefold.solver.core.api.score.HardSoftScore;
import ai.timefold.solver.service.definition.api.ModelConvertor;
import ai.timefold.solver.service.definition.api.domain.ModelConfig;

import org.acme.conferencescheduling.domain.ConferenceConstraintProperties;
import org.acme.conferencescheduling.domain.ConferenceSchedule;
import org.acme.conferencescheduling.domain.Room;
import org.acme.conferencescheduling.domain.Speaker;
import org.acme.conferencescheduling.domain.Talk;
import org.acme.conferencescheduling.domain.TalkType;
import org.acme.conferencescheduling.domain.Timeslot;
import org.acme.conferencescheduling.dto.ConferenceScheduleConfigOverrides;
import org.acme.conferencescheduling.dto.ConferenceScheduleInput;
import org.acme.conferencescheduling.dto.ConferenceScheduleOutput;
import org.acme.conferencescheduling.dto.RoomDTO;
import org.acme.conferencescheduling.dto.SpeakerDTO;
import org.acme.conferencescheduling.dto.TalkDTO;
import org.acme.conferencescheduling.dto.TalkTypeDTO;
import org.acme.conferencescheduling.dto.TimeslotDTO;

@ApplicationScoped
public class ConferenceScheduleModelConvertor
        implements
        ModelConvertor<HardSoftScore, ConferenceScheduleInput, ConferenceScheduleConfigOverrides, ConferenceSchedule, ConferenceScheduleOutput> {

    @Override
    public ConferenceScheduleInput applyOutputToInput(ConferenceScheduleInput modelInput,
            ConferenceScheduleOutput modelOutput) {
        Map<String, TalkDTO> outputTalks = modelOutput.talks().stream()
                .collect(Collectors.toMap(TalkDTO::code, talk -> talk));
        List<TalkDTO> updatedTalks = modelInput.talks().stream()
                .map(talk -> {
                    TalkDTO solved = outputTalks.get(talk.code());
                    if (solved == null) {
                        return talk;
                    }
                    return talk.withTimeslotId(solved.timeslotId()).withRoomId(solved.roomId());
                })
                .collect(Collectors.toList());
        return modelInput.withTalks(updatedTalks);
    }

    @Override
    public ConferenceSchedule toSolverModel(ConferenceScheduleInput modelInput,
            ModelConfig<ConferenceScheduleConfigOverrides> modelConfig,
            Optional<ConferenceScheduleOutput> lastModelOutput) {
        Map<String, TalkType> talkTypeMap = modelInput.talkTypes().stream()
                .collect(Collectors.toMap(TalkTypeDTO::name, dto -> new TalkType(dto.name()), (a, b) -> a,
                        java.util.LinkedHashMap::new));

        Map<String, Timeslot> timeslotMap = new java.util.LinkedHashMap<>();
        Set<Timeslot> timeslots = modelInput.timeslots().stream()
                .map(dto -> {
                    Timeslot timeslot = new Timeslot(dto.id(), LocalDateTime.parse(dto.startDateTime()),
                            LocalDateTime.parse(dto.endDateTime()), talkTypes(dto.talkTypeNames(), talkTypeMap),
                            new LinkedHashSet<>(dto.tags()));
                    timeslotMap.put(timeslot.getId(), timeslot);
                    return timeslot;
                })
                .collect(toCollection(LinkedHashSet::new));

        Map<String, Room> roomMap = new java.util.LinkedHashMap<>();
        Set<Room> rooms = modelInput.rooms().stream()
                .map(dto -> {
                    Room room = new Room(dto.id(), dto.name(), dto.capacity(),
                            talkTypes(dto.talkTypeNames(), talkTypeMap),
                            timeslotsByIds(dto.unavailableTimeslotIds(), timeslotMap),
                            new LinkedHashSet<>(dto.tags()));
                    roomMap.put(room.getId(), room);
                    return room;
                })
                .collect(toCollection(LinkedHashSet::new));

        Map<String, Speaker> speakerMap = new java.util.LinkedHashMap<>();
        Set<Speaker> speakers = modelInput.speakers().stream()
                .map(dto -> {
                    Speaker speaker = toSpeaker(dto, timeslotMap);
                    speakerMap.put(speaker.getId(), speaker);
                    return speaker;
                })
                .collect(toCollection(LinkedHashSet::new));

        Map<String, Talk> talkMap = new java.util.LinkedHashMap<>();
        Set<Talk> talks = modelInput.talks().stream()
                .map(dto -> {
                    Talk talk = toTalk(dto, talkTypeMap, speakerMap, timeslotMap, roomMap);
                    talkMap.put(talk.getCode(), talk);
                    return talk;
                })
                .collect(toCollection(LinkedHashSet::new));
        applyPrerequisites(modelInput.talks(), talkMap);

        ConferenceSchedule schedule = new ConferenceSchedule(modelInput.name(),
                new LinkedHashSet<>(talkTypeMap.values()), timeslots, rooms, speakers, talks);
        schedule.setConstraintProperties(new ConferenceConstraintProperties());
        applyConstraintWeightOverrides(schedule, modelConfig);
        applyLastOutput(talkMap, timeslotMap, roomMap, lastModelOutput);
        return schedule;
    }

    private static Set<TalkType> talkTypes(List<String> names, Map<String, TalkType> talkTypeMap) {
        return names.stream().map(talkTypeMap::get).collect(toCollection(LinkedHashSet::new));
    }

    private static SequencedSet<Timeslot> timeslotsByIds(List<String> ids, Map<String, Timeslot> timeslotMap) {
        return ids.stream().map(timeslotMap::get).collect(toCollection(LinkedHashSet::new));
    }

    private static Speaker toSpeaker(SpeakerDTO dto, Map<String, Timeslot> timeslotMap) {
        return new Speaker(dto.id(), dto.name(), timeslotsByIds(dto.unavailableTimeslotIds(), timeslotMap),
                new LinkedHashSet<>(dto.requiredTimeslotTags()), new LinkedHashSet<>(dto.preferredTimeslotTags()),
                new LinkedHashSet<>(dto.prohibitedTimeslotTags()), new LinkedHashSet<>(dto.undesiredTimeslotTags()),
                new LinkedHashSet<>(dto.requiredRoomTags()), new LinkedHashSet<>(dto.preferredRoomTags()),
                new LinkedHashSet<>(dto.prohibitedRoomTags()), new LinkedHashSet<>(dto.undesiredRoomTags()));
    }

    private static Talk toTalk(TalkDTO dto, Map<String, TalkType> talkTypeMap, Map<String, Speaker> speakerMap,
            Map<String, Timeslot> timeslotMap, Map<String, Room> roomMap) {
        List<Speaker> speakers = dto.speakerIds().stream().map(speakerMap::get).collect(Collectors.toList());
        Talk talk = new Talk(dto.code(), dto.title(), talkTypeMap.get(dto.talkTypeName()), speakers,
                new LinkedHashSet<>(dto.themeTrackTags()), new LinkedHashSet<>(dto.sectorTags()),
                new LinkedHashSet<>(dto.audienceTypes()), dto.audienceLevel(), new LinkedHashSet<>(dto.contentTags()),
                dto.language(), new LinkedHashSet<>(dto.requiredTimeslotTags()),
                new LinkedHashSet<>(dto.preferredTimeslotTags()), new LinkedHashSet<>(dto.prohibitedTimeslotTags()),
                new LinkedHashSet<>(dto.undesiredTimeslotTags()), new LinkedHashSet<>(dto.requiredRoomTags()),
                new LinkedHashSet<>(dto.preferredRoomTags()), new LinkedHashSet<>(dto.prohibitedRoomTags()),
                new LinkedHashSet<>(dto.undesiredRoomTags()), new LinkedHashSet<>(dto.mutuallyExclusiveTalksTags()),
                new LinkedHashSet<>(), dto.favoriteCount(), dto.crowdControlRisk());
        if (dto.timeslotId() != null) {
            talk.setTimeslot(timeslotMap.get(dto.timeslotId()));
        }
        if (dto.roomId() != null) {
            talk.setRoom(roomMap.get(dto.roomId()));
        }
        return talk;
    }

    private static void applyPrerequisites(List<TalkDTO> talkDtos, Map<String, Talk> talkMap) {
        for (TalkDTO dto : talkDtos) {
            if (dto.prerequisiteTalkCodes().isEmpty()) {
                continue;
            }
            SequencedSet<Talk> prerequisites = dto.prerequisiteTalkCodes().stream()
                    .map(talkMap::get)
                    .filter(java.util.Objects::nonNull)
                    .collect(toCollection(LinkedHashSet::new));
            talkMap.get(dto.code()).setPrerequisiteTalks(prerequisites);
        }
    }

    private static void applyConstraintWeightOverrides(ConferenceSchedule schedule,
            ModelConfig<ConferenceScheduleConfigOverrides> modelConfig) {
        if (modelConfig == null || modelConfig.overrides() == null) {
            return;
        }
        ConferenceScheduleConfigOverrides overrides = modelConfig.overrides();
        Map<String, HardSoftScore> weights = new HashMap<>();
        weights.put(ConferenceConstraintProperties.THEME_TRACK_CONFLICT,
                HardSoftScore.ofSoft(overrides.themeTrackConflictWeight()));
        weights.put(ConferenceConstraintProperties.THEME_TRACK_ROOM_STABILITY,
                HardSoftScore.ofSoft(overrides.themeTrackRoomStabilityWeight()));
        weights.put(ConferenceConstraintProperties.SECTOR_CONFLICT,
                HardSoftScore.ofSoft(overrides.sectorConflictWeight()));
        weights.put(ConferenceConstraintProperties.AUDIENCE_TYPE_DIVERSITY,
                HardSoftScore.ofSoft(overrides.audienceTypeDiversityWeight()));
        weights.put(ConferenceConstraintProperties.AUDIENCE_TYPE_THEME_TRACK_CONFLICT,
                HardSoftScore.ofSoft(overrides.audienceTypeThemeTrackConflictWeight()));
        weights.put(ConferenceConstraintProperties.AUDIENCE_LEVEL_DIVERSITY,
                HardSoftScore.ofSoft(overrides.audienceLevelDiversityWeight()));
        weights.put(ConferenceConstraintProperties.CONTENT_AUDIENCE_LEVEL_FLOW_VIOLATION,
                HardSoftScore.ofSoft(overrides.contentAudienceLevelFlowViolationWeight()));
        weights.put(ConferenceConstraintProperties.CONTENT_CONFLICT,
                HardSoftScore.ofSoft(overrides.contentConflictWeight()));
        weights.put(ConferenceConstraintProperties.LANGUAGE_DIVERSITY,
                HardSoftScore.ofSoft(overrides.languageDiversityWeight()));
        weights.put(ConferenceConstraintProperties.SAME_DAY_TALKS,
                HardSoftScore.ofSoft(overrides.sameDayTalksWeight()));
        weights.put(ConferenceConstraintProperties.POPULAR_TALKS,
                HardSoftScore.ofSoft(overrides.popularTalksWeight()));
        weights.put(ConferenceConstraintProperties.SPEAKER_PREFERRED_TIMESLOT_TAGS,
                HardSoftScore.ofSoft(overrides.speakerPreferredTimeslotTagsWeight()));
        weights.put(ConferenceConstraintProperties.SPEAKER_UNDESIRED_TIMESLOT_TAGS,
                HardSoftScore.ofSoft(overrides.speakerUndesiredTimeslotTagsWeight()));
        weights.put(ConferenceConstraintProperties.TALK_PREFERRED_TIMESLOT_TAGS,
                HardSoftScore.ofSoft(overrides.talkPreferredTimeslotTagsWeight()));
        weights.put(ConferenceConstraintProperties.TALK_UNDESIRED_TIMESLOT_TAGS,
                HardSoftScore.ofSoft(overrides.talkUndesiredTimeslotTagsWeight()));
        weights.put(ConferenceConstraintProperties.SPEAKER_PREFERRED_ROOM_TAGS,
                HardSoftScore.ofSoft(overrides.speakerPreferredRoomTagsWeight()));
        weights.put(ConferenceConstraintProperties.SPEAKER_UNDESIRED_ROOM_TAGS,
                HardSoftScore.ofSoft(overrides.speakerUndesiredRoomTagsWeight()));
        weights.put(ConferenceConstraintProperties.TALK_PREFERRED_ROOM_TAGS,
                HardSoftScore.ofSoft(overrides.talkPreferredRoomTagsWeight()));
        weights.put(ConferenceConstraintProperties.TALK_UNDESIRED_ROOM_TAGS,
                HardSoftScore.ofSoft(overrides.talkUndesiredRoomTagsWeight()));
        weights.put(ConferenceConstraintProperties.SPEAKER_MAKESPAN,
                HardSoftScore.ofSoft(overrides.speakerMakespanWeight()));
        schedule.setConstraintWeightOverrides(ConstraintWeightOverrides.of(weights));
    }

    private static void applyLastOutput(Map<String, Talk> talkMap, Map<String, Timeslot> timeslotMap,
            Map<String, Room> roomMap, Optional<ConferenceScheduleOutput> lastModelOutput) {
        if (lastModelOutput.isEmpty()) {
            return;
        }
        for (TalkDTO solved : lastModelOutput.get().talks()) {
            Talk talk = talkMap.get(solved.code());
            if (talk == null) {
                continue;
            }
            if (solved.timeslotId() != null) {
                talk.setTimeslot(timeslotMap.get(solved.timeslotId()));
            }
            if (solved.roomId() != null) {
                talk.setRoom(roomMap.get(solved.roomId()));
            }
        }
    }

    @Override
    public ConferenceScheduleOutput toModelOutput(ConferenceSchedule solverModel) {
        List<TalkTypeDTO> talkTypes = solverModel.getTalkTypes().stream()
                .map(talkType -> new TalkTypeDTO(talkType.getName())).collect(Collectors.toList());
        List<TimeslotDTO> timeslots = solverModel.getTimeslots().stream().map(this::toDTO).collect(Collectors.toList());
        List<RoomDTO> rooms = solverModel.getRooms().stream().map(this::toDTO).collect(Collectors.toList());
        List<SpeakerDTO> speakers = solverModel.getSpeakers().stream().map(this::toDTO).collect(Collectors.toList());
        List<TalkDTO> talks = solverModel.getTalks().stream().map(this::toDTO).collect(Collectors.toList());
        String score = solverModel.getScore() == null ? "" : solverModel.getScore().toString();
        return new ConferenceScheduleOutput(solverModel.getName(), talkTypes, timeslots, rooms, speakers, talks, score);
    }

    private TimeslotDTO toDTO(Timeslot timeslot) {
        List<String> talkTypeNames = timeslot.getTalkTypes().stream().map(TalkType::getName).collect(Collectors.toList());
        return new TimeslotDTO(timeslot.getId(), timeslot.getStartDateTime().toString(),
                timeslot.getEndDateTime().toString(), talkTypeNames, List.copyOf(timeslot.getTags()));
    }

    private RoomDTO toDTO(Room room) {
        List<String> talkTypeNames = room.getTalkTypes().stream().map(TalkType::getName).collect(Collectors.toList());
        List<String> unavailableTimeslotIds =
                room.getUnavailableTimeslots().stream().map(Timeslot::getId).collect(Collectors.toList());
        return new RoomDTO(room.getId(), room.getName(), room.getCapacity(), talkTypeNames, unavailableTimeslotIds,
                List.copyOf(room.getTags()));
    }

    private SpeakerDTO toDTO(Speaker speaker) {
        List<String> unavailableTimeslotIds =
                speaker.getUnavailableTimeslots().stream().map(Timeslot::getId).collect(Collectors.toList());
        return new SpeakerDTO(speaker.getId(), speaker.getName(), unavailableTimeslotIds,
                List.copyOf(speaker.getRequiredTimeslotTags()), List.copyOf(speaker.getPreferredTimeslotTags()),
                List.copyOf(speaker.getProhibitedTimeslotTags()), List.copyOf(speaker.getUndesiredTimeslotTags()),
                List.copyOf(speaker.getRequiredRoomTags()), List.copyOf(speaker.getPreferredRoomTags()),
                List.copyOf(speaker.getProhibitedRoomTags()), List.copyOf(speaker.getUndesiredRoomTags()));
    }

    private TalkDTO toDTO(Talk talk) {
        List<String> speakerIds = talk.getSpeakers().stream().map(Speaker::getId).collect(Collectors.toList());
        List<String> prerequisiteCodes =
                talk.getPrerequisiteTalks().stream().map(Talk::getCode).collect(Collectors.toList());
        String timeslotId = talk.getTimeslot() == null ? null : talk.getTimeslot().getId();
        String roomId = talk.getRoom() == null ? null : talk.getRoom().getId();
        return new TalkDTO(talk.getCode(), talk.getTitle(), talk.getTalkType().getName(), speakerIds,
                List.copyOf(talk.getThemeTrackTags()), List.copyOf(talk.getSectorTags()),
                List.copyOf(talk.getAudienceTypes()), talk.getAudienceLevel(), List.copyOf(talk.getContentTags()),
                talk.getLanguage(), List.copyOf(talk.getRequiredTimeslotTags()),
                List.copyOf(talk.getPreferredTimeslotTags()), List.copyOf(talk.getProhibitedTimeslotTags()),
                List.copyOf(talk.getUndesiredTimeslotTags()), List.copyOf(talk.getRequiredRoomTags()),
                List.copyOf(talk.getPreferredRoomTags()), List.copyOf(talk.getProhibitedRoomTags()),
                List.copyOf(talk.getUndesiredRoomTags()), List.copyOf(talk.getMutuallyExclusiveTalksTags()),
                prerequisiteCodes, talk.getFavoriteCount(), talk.getCrowdControlRisk(), timeslotId, roomId);
    }
}
