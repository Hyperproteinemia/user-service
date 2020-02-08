package tk.laurenfrost.users.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Data
public class Request {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AppUser to;

    @ManyToOne
    private AppUser from;

    private Instant date;

    private String message;


}
