package org.acme.vehiclerouting.demo;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import ai.timefold.solver.service.definition.api.data.AbstractBasicDemoDataGenerator;
import ai.timefold.solver.service.definition.api.domain.Configuration;
import ai.timefold.solver.service.definition.api.domain.ModelConfig;
import ai.timefold.solver.service.definition.api.domain.ModelRequest;
import ai.timefold.solver.service.definition.api.domain.RunConfiguration;
import ai.timefold.solver.service.definition.api.termination.SolverTerminationConfig;

import org.acme.vehiclerouting.dto.VehicleRoutingConfigOverrides;
import org.acme.vehiclerouting.dto.VehicleRoutingInput;

@ApplicationScoped
public class DemoDataGenerator
        extends
        AbstractBasicDemoDataGenerator<VehicleRoutingInput, VehicleRoutingConfigOverrides> {

    private static final Duration DEMO_SPENT_LIMIT = Duration.ofSeconds(30);

    @Override
    protected ModelRequest<VehicleRoutingInput, VehicleRoutingConfigOverrides> generateBasicDemoDataRequest() {
        VehicleRoutingInput problem = DemoDataBuilder.builder().build();
        SolverTerminationConfig termination = new SolverTerminationConfig(DEMO_SPENT_LIMIT, null);
        RunConfiguration runConfiguration = new RunConfiguration("BASIC", termination);
        VehicleRoutingConfigOverrides overrides = new VehicleRoutingConfigOverrides();
        ModelConfig<VehicleRoutingConfigOverrides> modelConfig = new ModelConfig<>(overrides);
        Configuration<VehicleRoutingConfigOverrides> configuration = new Configuration<>(runConfiguration, modelConfig);
        return new ModelRequest<>(configuration, problem);
    }
}
