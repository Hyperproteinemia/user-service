package tk.laurenfrost.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.service.AuthService;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        AppUser user = authService.findUserByUsername(username);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User "+username+" is not found.\"}");
        else
            return ResponseEntity.ok(user);
    }

}
