package tk.laurenfrost.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Contact;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.repository.RequestRepository;
import tk.laurenfrost.users.service.AuthService;
import tk.laurenfrost.users.service.RequestService;

@RestController
@EnableAutoConfiguration
public class AppUserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RequestService requestService;

    @GetMapping("/user/{hisUsername}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String hisUsername, @RequestHeader(name = "username") String username) {
        AppUser him = authService.findUserByUsername(hisUsername);
        if (him == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User "+hisUsername+" is not found.\"}");
        else
            return ResponseEntity.ok(him);
    }

    @PatchMapping("/user")
    public ResponseEntity<?> updateUser(@RequestHeader(name = "username") String username, @RequestBody AppUser appUser) {
        appUser.setUsername(username);
        appUser = authService.updateUser(appUser);
        return ResponseEntity.ok(appUser);
    }

}
