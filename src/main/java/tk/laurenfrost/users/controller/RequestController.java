package tk.laurenfrost.users.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.service.AuthService;
import tk.laurenfrost.users.service.RequestService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class RequestController {

    final
    RequestService requestService;

    final
    AuthService authService;

    public RequestController(RequestService requestService, AuthService authService) {
        this.requestService = requestService;
        this.authService = authService;
    }

    @PostMapping("/user/{toUsername}/request")
    ResponseEntity<?> createRequest(@PathVariable String toUsername,
                                    @RequestHeader(name = "username") String username,
                                    @RequestBody String message) {
        AppUser you = authService.findUserByUsername(username);
        AppUser him = authService.findUserByUsername(toUsername);
        Request request = requestService.getByUsers(you, him);
        if (request != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("\"message\": \"Request already exists\"");
        }
        request = requestService.createRequest(you, him, message);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/user/{fromUsername}/request/accept")
    ResponseEntity<?> acceptRequest(@PathVariable String fromUsername, @RequestHeader(name = "username") String username) {
        AppUser you = authService.findUserByUsername(username);
        AppUser him = authService.findUserByUsername(fromUsername);
        Request request = requestService.getByUsers(him, you);
        request = requestService.acceptRequest(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/user/{fromUsername}/request/decline")
    ResponseEntity<?> declineRequest(@PathVariable String fromUsername, @RequestHeader(name = "username") String username) {
        AppUser you = authService.findUserByUsername(username);
        AppUser him = authService.findUserByUsername(fromUsername);
        Request request = requestService.getByUsers(him, you);
        request = requestService.declineRequest(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("/user/request/incoming")
    ResponseEntity<?> getIncomingRequests(@RequestHeader(name = "username") String username) {
        AppUser you = authService.findUserByUsername(username);
        List<Request> requests = requestService.getAllTo(you);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/user/request/mine")
    ResponseEntity<?> getMineRequests(@RequestHeader(name = "username") String username) {
        AppUser you = authService.findUserByUsername(username);
        List<Request> requests = requestService.getAllFrom(you);
        return ResponseEntity.ok(requests);
    }
}
