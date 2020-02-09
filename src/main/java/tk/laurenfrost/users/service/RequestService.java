package tk.laurenfrost.users.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tk.laurenfrost.users.entity.AppUser;
import tk.laurenfrost.users.entity.Request;
import tk.laurenfrost.users.repository.RequestRepository;

import java.time.Instant;
import java.util.List;

@Service
public class RequestService {
    private final JavaMailSender javaMailSender;

    final
    RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository, JavaMailSender javaMailSender) {
        this.requestRepository = requestRepository;
        this.javaMailSender = javaMailSender;
    }

    public Request getByUsers(AppUser from, AppUser to) {
        return requestRepository.findByAppUsers(from, to);
    }

    public Request acceptRequest(Request request) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(request.getFrom().getEmail());
        msg.setSubject("Your request accepted!");
        msg.setText(request.getFrom().getUsername() + " has accepted your request.");
        javaMailSender.send(msg);

        request.setConfirmed(true);
        return requestRepository.save(request);
    }

    public Request declineRequest(Request request) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(request.getTo().getEmail());
        msg.setSubject("Your request declined!");
        msg.setText(request.getFrom().getUsername() + " has accepted your request.");
        javaMailSender.send(msg);

        request.setConfirmed(false);
        return requestRepository.save(request);
    }

    public Request createRequest(AppUser from, AppUser to, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to.getEmail());
        msg.setSubject("You have someone interested in you!");
        msg.setText(from.getUsername() + " : " + message);
        javaMailSender.send(msg);

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
