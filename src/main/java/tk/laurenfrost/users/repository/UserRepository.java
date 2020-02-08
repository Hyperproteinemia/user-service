package tk.laurenfrost.users.repository;

import org.springframework.data.repository.CrudRepository;
import tk.laurenfrost.users.entity.AppUser;

public interface UserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsernameIgnoreCase(String username);
}