package com.roshka.tests.springbootangularkeycloaklogin.web;

import com.roshka.tests.springbootangularkeycloaklogin.bean.OAuth2TokenResponse;
import com.roshka.tests.springbootangularkeycloaklogin.config.KeycloakConfig;
import com.roshka.tests.springbootangularkeycloaklogin.session.SBAKLSession;
import com.roshka.tests.springbootangularkeycloaklogin.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    private String appName;

    private KeycloakConfig keycloakConfig;

    @Autowired
    public HomeController(KeycloakConfig keycloakConfig)
    {
        this.keycloakConfig = keycloakConfig;
    }


    @GetMapping("/")
    public String homePage(
            HttpSession session,
            Model model
    )
    {

        SBAKLSession sbaklSession = SessionManager.getCurrentSession(session);
        if (sbaklSession != null) {
            model.addAttribute("appName", appName);
            model.addAttribute("session", session);
            return "home";
        }

        String redirect = UriComponentsBuilder.fromHttpUrl(keycloakConfig.getLoginURI())
                .queryParam("scope", keycloakConfig.getScope())
                .queryParam("response_type", "code")
                .queryParam("client_id", keycloakConfig.getClientId())
                .queryParam("redirect_uri", keycloakConfig.getRedirectURI())
                .toUriString();

        return "redirect:" + redirect;
    }

    @GetMapping("/auth-result")
    public String authResult(
            Model model,
            String code,
            @RequestParam("session_state") String sessionState
    ) {
        String basicAuth = new String(Base64.getEncoder().encode(
                (keycloakConfig.getClientId() + ":" + keycloakConfig.getClientSecret()).getBytes(StandardCharsets.UTF_8)
        ));

        WebClient client = WebClient.builder()
                .baseUrl(keycloakConfig.getTokenURI())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                .build();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("redirect_uri", keycloakConfig.getRedirectURI());
        params.add("grant_type", "authorization_code");
        //params.add("client_id", keycloakConfig.getClientId());
        //params.add("client_secret", keycloakConfig.getClientSecret());

        final OAuth2TokenResponse oAuth2TokenResponse = client
                .post()
                .body(
                        BodyInserters.fromFormData(params)
                )
                .retrieve()
                .bodyToFlux(OAuth2TokenResponse.class)
                .blockLast();

        model.addAttribute("code", code);
        model.addAttribute("sessionState", sessionState);
        return "loginok";
    }

}
