package com.roshka.tests.springbootangularkeycloaklogin.session;

import com.roshka.tests.springbootangularkeycloaklogin.bean.OAuth2TokenResponse;
import com.roshka.tests.springbootangularkeycloaklogin.bean.Profile;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class SBAKLSession implements Serializable {

    private ZonedDateTime created;
    private ZonedDateTime lastSeen;
    private Profile profile;
    private OAuth2TokenResponse oAuth2TokenResponse;

    public SBAKLSession()
    {
        this.created = ZonedDateTime.now();
        this.lastSeen = this.created;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(ZonedDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public OAuth2TokenResponse getoAuth2TokenResponse() {
        return oAuth2TokenResponse;
    }

    public void setoAuth2TokenResponse(OAuth2TokenResponse oAuth2TokenResponse) {
        this.oAuth2TokenResponse = oAuth2TokenResponse;
    }
}
