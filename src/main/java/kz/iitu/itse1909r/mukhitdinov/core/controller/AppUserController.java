package kz.iitu.itse1909r.mukhitdinov.core.controller;

import io.swagger.annotations.ApiParam;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.UserCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.service.AppUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@Lazy
@RestController
@RequestMapping("api/user")
public class AppUserController {
    private AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    public String signup(@ApiParam("Signup User") @RequestBody UserCreateRequestModel user) {
        return userService.signup(user);
    }
}
