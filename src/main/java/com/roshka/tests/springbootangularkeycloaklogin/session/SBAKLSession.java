package com.roshka.tests.springbootangularkeycloaklogin.session;

import com.roshka.tests.springbootangularkeycloaklogin.bean.Profile;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class SBAKLSession implements Serializable {

    private ZonedDateTime created;
    private ZonedDateTime lastSeen;
    private Profile profile;

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
}
