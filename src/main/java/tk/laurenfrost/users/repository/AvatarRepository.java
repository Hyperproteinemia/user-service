package tk.laurenfrost.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.users.entity.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, String> {

}
