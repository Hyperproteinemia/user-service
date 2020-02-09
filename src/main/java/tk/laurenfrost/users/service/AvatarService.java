package tk.laurenfrost.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.laurenfrost.users.entity.Avatar;
import tk.laurenfrost.users.repository.AvatarRepository;

import java.io.IOException;

@Service("avatarService")
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar saveAvatar(MultipartFile file, String username) {
        try {
            Avatar avatar = avatarRepository.findById(username).orElse(null);
            if (avatar == null) {
                avatar = new Avatar(username, file.getContentType(), file.getBytes());
            } else {
                avatar.setData(file.getBytes());
                avatar.setFileType(file.getContentType());
            }
            avatar = avatarRepository.save(avatar);
            return avatar;
        } catch (IOException ex) {
            System.out.println("SAD");
            return null;
        }
    }

    public Avatar getAvatar(String username) {
        return avatarRepository.findById(username).orElse(null);
    }
}
