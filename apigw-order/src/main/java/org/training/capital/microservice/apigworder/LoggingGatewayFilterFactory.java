package org.training.capital.microservice.apigworder;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.LoggingConfig> {
    private static final Logger logger = LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    public LoggingGatewayFilterFactory() {
        super(LoggingConfig.class);
    }

    @Override
    public GatewayFilter apply(final LoggingConfig config) {
        return (e, c) -> {
            System.out.println("Config : " + config);
            if (logger.isInfoEnabled()) {
                logger.info("[LoggingGatewayFilterFactory][apply]-> Request logger : "
                            + config.prefix
                            + " - "
                            + e.getRequest());
            }
            return c.filter(e)
                    .then(Mono.fromRunnable(() -> {
                        if (logger.isInfoEnabled()) {
                            logger.info("[LoggingGatewayFilterFactory][apply]-> Response logger : "
                                        + config.prefix
                                        + " - "
                                        + e.getResponse());
                        }
                    }));
        };
    }

    @Data
    public static class LoggingConfig {
        String logLevel = "info";
        String prefix   = "pre";
    }
}
