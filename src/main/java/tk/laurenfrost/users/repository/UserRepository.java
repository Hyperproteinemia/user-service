package tk.laurenfrost.users.repository;

import tk.laurenfrost.users.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsernameIgnoreCase(String username);
}