package tk.laurenfrost.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.repository.UserRepository;

@Service("authService")
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    public AppUser createUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.save(user);
    }
}