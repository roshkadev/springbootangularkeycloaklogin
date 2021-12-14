package com.roshka.tests.springbootangularkeycloaklogin.api;

import com.roshka.tests.springbootangularkeycloaklogin.bean.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAPI {

    @GetMapping("/whoami")
    public Profile whoAmI()
    {
        Profile p = new Profile();
        p.setUserId("pablo");
        p.setFullName("Pablo Santa Cruz");
        p.setUserName("pablo");
        p.setPictureURL("https://www.roshka.com/media/images/pablo-santacruz.2e16d0ba.fill-100x100.png");
        return p;
    }


}
