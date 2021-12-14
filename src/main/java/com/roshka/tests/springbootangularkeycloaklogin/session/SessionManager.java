package com.roshka.tests.springbootangularkeycloaklogin.session;

import javax.servlet.http.HttpSession;

public class SessionManager {

    public static String CURRENT_SESSION_KEY = "session.key";

    public static SBAKLSession getCurrentSession(HttpSession session)
    {
        Object o = session.getAttribute(CURRENT_SESSION_KEY);
        return o instanceof SBAKLSession ? (SBAKLSession) o : null;
    }


}
