package com.aaronnbrock.hellodropwizard;


import com.aaronnbrock.hellodropwizard.health.TemplateHealthCheck;
import com.aaronnbrock.hellodropwizard.resources.HelloWorldResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class HelloDropWizardApp extends Application<HelloDropWizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloDropWizardApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloDropWizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloDropWizardConfiguration configuration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
