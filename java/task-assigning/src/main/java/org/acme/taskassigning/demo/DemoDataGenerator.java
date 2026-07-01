package org.acme.taskassigning.demo;

import java.time.Duration;

import jakarta.enterprise.context.ApplicationScoped;

import ai.timefold.solver.service.definition.api.data.AbstractBasicDemoDataGenerator;
import ai.timefold.solver.service.definition.api.domain.Configuration;
import ai.timefold.solver.service.definition.api.domain.ModelConfig;
import ai.timefold.solver.service.definition.api.domain.ModelRequest;
import ai.timefold.solver.service.definition.api.domain.RunConfiguration;
import ai.timefold.solver.service.definition.api.termination.SolverTerminationConfig;

import org.acme.taskassigning.dto.TaskAssigningConfigOverrides;
import org.acme.taskassigning.dto.TaskAssigningInput;

@ApplicationScoped
public class DemoDataGenerator
        extends
        AbstractBasicDemoDataGenerator<TaskAssigningInput, TaskAssigningConfigOverrides> {

    private static final Duration DEMO_SPENT_LIMIT = Duration.ofSeconds(30);

    @Override
    protected ModelRequest<TaskAssigningInput, TaskAssigningConfigOverrides> generateBasicDemoDataRequest() {
        TaskAssigningInput problem = DemoDataBuilder.builder().build();
        SolverTerminationConfig termination = new SolverTerminationConfig(DEMO_SPENT_LIMIT, null);
        RunConfiguration runConfiguration = new RunConfiguration("BASIC", termination);
        TaskAssigningConfigOverrides overrides = new TaskAssigningConfigOverrides();
        ModelConfig<TaskAssigningConfigOverrides> modelConfig = new ModelConfig<>(overrides);
        Configuration<TaskAssigningConfigOverrides> configuration = new Configuration<>(runConfiguration, modelConfig);
        return new ModelRequest<>(configuration, problem);
    }
}
