package org.acme.conferencescheduling.demo;

import jakarta.enterprise.context.ApplicationScoped;

import ai.timefold.solver.service.definition.api.data.AbstractBasicDemoDataGenerator;
import ai.timefold.solver.service.definition.api.domain.Configuration;
import ai.timefold.solver.service.definition.api.domain.ModelConfig;
import ai.timefold.solver.service.definition.api.domain.ModelRequest;
import ai.timefold.solver.service.definition.api.domain.RunConfiguration;

import org.acme.conferencescheduling.dto.ConferenceScheduleConfigOverrides;
import org.acme.conferencescheduling.dto.ConferenceScheduleInput;

@ApplicationScoped
public class DemoDataGenerator
        extends AbstractBasicDemoDataGenerator<ConferenceScheduleInput, ConferenceScheduleConfigOverrides> {

    @Override
    protected ModelRequest<ConferenceScheduleInput, ConferenceScheduleConfigOverrides> generateBasicDemoDataRequest() {
        ConferenceScheduleInput problem = DemoDataBuilder.builder().build();
        Configuration<ConferenceScheduleConfigOverrides> configuration = new Configuration<>(
                new RunConfiguration("BASIC"), new ModelConfig<>(new ConferenceScheduleConfigOverrides()));
        return new ModelRequest<>(configuration, problem);
    }
}
