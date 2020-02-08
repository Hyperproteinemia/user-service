package tk.laurenfrost.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.service.AuthService;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid AppUser user, BindingResult bindingResult) {
        AppUser userExists = authService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("There is already a user registered with the username provided");
        } else {

            user = authService.createUser(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);

        }
    }

    @GetMapping("/self")
    public ResponseEntity<?> getSelfUser(@RequestHeader(name = "username") String username) {
        AppUser user = authService.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
    }
}
