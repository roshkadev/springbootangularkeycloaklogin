package com.roshka.tests.springbootangularkeycloaklogin.session;

import org.springframework.web.server.WebSession;

public class SessionManager {

    public static String CURRENT_SESSION_KEY = "session.key";
    public static String CURRENT_SESSION_OAUTH2_TOKEN_KEY = "session.oauth2_token.key";


    public static SBAKLSession getCurrentSession(WebSession session)
    {
        Object o = session.getAttribute(CURRENT_SESSION_KEY);
        return o instanceof SBAKLSession ? (SBAKLSession) o : null;
    }

}
