package tk.laurenfrost.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(AppUser user);
}
