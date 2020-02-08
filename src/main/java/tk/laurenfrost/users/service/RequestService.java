package tk.laurenfrost.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.repository.RequestRepository;

import java.time.Instant;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request getByUsers(AppUser from, AppUser to) {
        return requestRepository.findByAppUsers(from, to);
    }

    public void confirmRequest(Request request) {
        request.setConfirmed(true);
        requestRepository.save(request);
    }

    public void createRequest(AppUser from, AppUser to, String message) {
        Request request = new Request();
        request.setDate(Instant.now());
        request.setConfirmed(false);
        request.setMessage(message);
        request.setTo(to);
        request.setFrom(from);
        requestRepository.save(request);
    }

    public List<Request> getAllTo(AppUser to) {
        return requestRepository.findByTo(to);
    }

    public List<Request> getAllFrom(AppUser from) {
        return requestRepository.findByFrom(from);
    }
}
