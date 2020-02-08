package tk.laurenfrost.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    //@JsonBackReference
    private AppUser to;

    @ManyToOne
    //@JsonBackReference
    private AppUser from;

    private Instant date;

    private String message;


}
