package tk.laurenfrost.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select req from Request req where req.from=?1 and req.to=?2")
    Request findByAppUsers(AppUser from, AppUser _to);

    List<Request> findByFrom(AppUser from);

    List<Request> findByTo(AppUser to);

}
