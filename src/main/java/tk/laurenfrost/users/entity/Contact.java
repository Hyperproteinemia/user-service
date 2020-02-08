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
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    ContactType type;

    @NotEmpty
    private String value;

}
