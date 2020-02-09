package tk.laurenfrost.users.service;

import org.springframework.stereotype.Service;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.repository.RequestRepository;

import java.time.Instant;
import java.util.List;

@Service
public class RequestService {

    final
    RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request getByUsers(AppUser from, AppUser to) {
        return requestRepository.findByAppUsers(from, to);
    }

    public Request acceptRequest(Request request) {
        request.setConfirmed(true);
        return requestRepository.save(request);
    }

    public Request declineRequest(Request request) {
        request.setConfirmed(false);
        return requestRepository.save(request);
    }

    public Request createRequest(AppUser from, AppUser to, String message) {
        Request request = new Request();
        request.setDate(Instant.now());
        request.setConfirmed(false);
        request.setMessage(message);
        request.setTo(to);
        request.setFrom(from);
        return requestRepository.save(request);
    }

    public List<Request> getAllTo(AppUser to) {
        return requestRepository.findByTo(to);
    }

    public List<Request> getAllFrom(AppUser from) {
        return requestRepository.findByFrom(from);
    }
}
