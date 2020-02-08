package tk.laurenfrost.users.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Contact;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.service.AuthService;
import tk.laurenfrost.users.service.ContactService;
import tk.laurenfrost.users.service.RequestService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ContactController {

    final
    ContactService contactService;

    final
    RequestService requestService;

    final
    AuthService authService;

    public ContactController(ContactService contactService, RequestService requestService, AuthService authService) {
        this.contactService = contactService;
        this.requestService = requestService;
        this.authService = authService;
    }

    @GetMapping("/user/{hisUsername}/contacts")
    ResponseEntity<?> getUserContacts(@PathVariable String hisUsername, @RequestHeader(name = "username") String username) {
        AppUser him = authService.findUserByUsername(hisUsername);
        AppUser you = authService.findUserByUsername(username);

        if (him == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User " + hisUsername + " is not found.\"}");
        }

        boolean approved = false;
        if (him.getPrivateContacts()) {
            Request request = requestService.getByUsers(you, him);
            if (request != null && request.isConfirmed()) {
                approved = true;
            }
        }

        if (him.equals(you)) approved = true;

        if (approved) {
            List<Contact> contacts = contactService.getUserContacts(him);
            return ResponseEntity.ok(contacts);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\": \"User "+hisUsername+" didn't share his contacts.\"}");
        }
    }

    @PatchMapping("/user/contacts")
    ResponseEntity<?> updateContacts(@RequestHeader(name = "username") String username, @RequestBody List<Contact> contacts) {
        // TODO: check that theese are yours
        contacts = contactService.updateContacts(contacts);
        return ResponseEntity.ok(contacts);
    }

}
