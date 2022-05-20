package kz.iitu.itse1909r.mukhitdinov.core.controller;

import io.swagger.annotations.ApiParam;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.user.UserCreateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.user.UserResponseBody;
import kz.iitu.itse1909r.mukhitdinov.core.controller.models.user.UserUpdateRequestModel;
import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.service.AppUserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Lazy
@RestController
@RequestMapping("api/user")
public class AppUserController {
    private AppUserService userService;

    @PostConstruct
    public void logPostConstruct() {
        System.out.println("AppUserController controller created!");
    }

    @PreDestroy
    public void logPreDestroy() {
        System.out.println("AppUserController destroyed!");
    }

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

    @GetMapping("/{id}")
    @ResponseBody
    public UserResponseBody getUser(@PathVariable Long id) {
        AppUser user = userService.getById(id);
        return new UserResponseBody(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UserResponseBody updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestModel user) {
        AppUser updatedUser = userService.updateUser(id, user);
        return new UserResponseBody(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getFirstName(), updatedUser.getLastName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
