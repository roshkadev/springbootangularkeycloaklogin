package com.roshka.tests.springbootangularkeycloaklogin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyAPI {

    private SecurityParameters securityParameters;

    @Autowired
    public ProxyAPI(SecurityParameters securityParameters) {
        this.securityParameters = securityParameters;
    }

    @Bean
    public RouteLocator proxyRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/proxy1/*")
                        .filters(f -> f.filter(securityParameters))
                        .uri("http://localhost:8899")

                )
        .build();
    }

}
