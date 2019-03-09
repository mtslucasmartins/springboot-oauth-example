package br.com.ottimizza.auth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public Principal getCurrentLoggedInUser(Principal user) {
        return user;
    }

}
