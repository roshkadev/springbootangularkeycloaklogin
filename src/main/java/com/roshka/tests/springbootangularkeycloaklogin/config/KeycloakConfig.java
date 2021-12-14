package com.roshka.tests.springbootangularkeycloaklogin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sbakl.keycloak")
public class KeycloakConfig {

    private String loginURI;
    private String scope;
    private String clientId;
    private String clientSecret;
    private String redirectURI;
    private String tokenURI;
    private String userInfoURI;
    private String logoutURI;

    public String getLoginURI() {
        return loginURI;
    }

    public void setLoginURI(String loginURI) {
        this.loginURI = loginURI;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getTokenURI() {
        return tokenURI;
    }

    public void setTokenURI(String tokenURI) {
        this.tokenURI = tokenURI;
    }

    public String getUserInfoURI() {
        return userInfoURI;
    }

    public void setUserInfoURI(String userInfoURI) {
        this.userInfoURI = userInfoURI;
    }

    public String getLogoutURI() {
        return logoutURI;
    }

    public void setLogoutURI(String logoutURI) {
        this.logoutURI = logoutURI;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
