package tk.laurenfrost.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class AppUser {

    @Id
    @NotNull
    @NotEmpty
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotEmpty
    private String password;

    private String email;

    private String name;

    private String surname;

//    private Set<Language> languages;

    private String country;

//    TODO: liked articles

    private String bio;

    private String city;

    @OneToMany(mappedBy = "user")
    private Set<Contact> contacts;

    @Lob
    private byte[] avatar;

    @JsonBackReference
    @OneToMany(mappedBy = "from")
    private Set<Request> out_request;

    @JsonBackReference
    @OneToMany(mappedBy = "to")
    private Set<Request> in_request;

}