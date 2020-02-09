package tk.laurenfrost.users.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.laurenfrost.users.entity.Avatar;
import tk.laurenfrost.users.service.AvatarService;

@RestController
@EnableAutoConfiguration
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/user/{username}/avatar")
    public ResponseEntity<?> uploadFile(@RequestHeader String username, @RequestParam("file") MultipartFile file) {
        Avatar avatar = avatarService.saveAvatar(file, username);

        return ResponseEntity.ok().body("{}");
    }

    @ResponseBody
    @GetMapping("/user/{username}/avatar")
    public ResponseEntity<Resource> getAvatar(@PathVariable String username) {
        Avatar avatar = avatarService.getAvatar(username);
        if (avatar == null) {
            Resource img = new ClassPathResource("image/default.jpg");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(img);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(avatar.getFileType()))
                .body(new ByteArrayResource(avatar.getData()));
    }

}
