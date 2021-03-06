package tk.laurenfrost.users.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Contact {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private AppUser user;

    @Enumerated(EnumType.STRING)
    ContactType type;

    @NotEmpty
    private String value;

}
