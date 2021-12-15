package com.roshka.tests.springbootangularkeycloaklogin.api;

import com.roshka.tests.springbootangularkeycloaklogin.session.SBAKLSession;
import com.roshka.tests.springbootangularkeycloaklogin.session.SessionManager;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class SecurityParameters implements GatewayFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        final Mono<WebSession> monoSession = exchange.getSession();
        AtomicReference<SBAKLSession> session = new AtomicReference<>();
        monoSession.subscribe(webSession -> {
            session.set(SessionManager.getCurrentSession(webSession));
        });

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header("api-key", session.get().getoAuth2TokenResponse().getAccess_token()).build();
        ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
        return chain.filter(mutatedExchange);
    }
}
