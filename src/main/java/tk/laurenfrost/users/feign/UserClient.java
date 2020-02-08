package tk.laurenfrost.users.feign;

import tk.laurenfrost.users.dto.Response;
import tk.laurenfrost.users.entity.AppUser;
import feign.Headers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = "application/json")
    @Headers("Content-Type: application/json")
    Response createUser(AppUser user);
}
