package tk.laurenfrost.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.service.UserService;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid AppUser user, BindingResult bindingResult) {
        AppUser userExists = userService.findUserByUsername(user.getUsername());
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
            userService.saveUser(user);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("User created");

        }
    }

    @GetMapping("/self")
    public ResponseEntity<?> getSelfUser(@RequestHeader String username) {
        AppUser user = userService.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
        }
    }
}
