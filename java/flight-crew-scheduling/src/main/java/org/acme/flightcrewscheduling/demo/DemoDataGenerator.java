package org.acme.flightcrewscheduling.demo;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import ai.timefold.solver.service.definition.api.data.AbstractBasicDemoDataGenerator;
import ai.timefold.solver.service.definition.api.domain.Configuration;
import ai.timefold.solver.service.definition.api.domain.ModelConfig;
import ai.timefold.solver.service.definition.api.domain.ModelRequest;
import ai.timefold.solver.service.definition.api.domain.RunConfiguration;
import ai.timefold.solver.service.definition.api.termination.SolverTerminationConfig;

import org.acme.flightcrewscheduling.dto.FlightCrewScheduleConfigOverrides;
import org.acme.flightcrewscheduling.dto.FlightCrewScheduleInput;

@ApplicationScoped
public class DemoDataGenerator
        extends
        AbstractBasicDemoDataGenerator<FlightCrewScheduleInput, FlightCrewScheduleConfigOverrides> {

    @Override
    protected ModelRequest<FlightCrewScheduleInput, FlightCrewScheduleConfigOverrides> generateBasicDemoDataRequest() {
        FlightCrewScheduleInput problem = DemoDataBuilder.builder()
                .setFlightCount(14)
                .setDayCount(5)
                .build();
        SolverTerminationConfig termination = new SolverTerminationConfig(Duration.ofSeconds(30), null);
        Configuration<FlightCrewScheduleConfigOverrides> configuration = new Configuration<>(
                new RunConfiguration("BASIC", termination), new ModelConfig<>(new FlightCrewScheduleConfigOverrides()));
        return new ModelRequest<>(configuration, problem);
    }
}
