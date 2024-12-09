package org.training.capital.microservice.mscommon.error;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ms")
public class MsProps {
    private String boundedContext;
    private String microservice;

    public String getBoundedContext() {
        return boundedContext;
    }

    public void setBoundedContext(final String boundedContextParam) {
        boundedContext = boundedContextParam;
    }

    public String getMicroservice() {
        return microservice;
    }

    public void setMicroservice(final String microserviceParam) {
        microservice = microserviceParam;
    }
}
