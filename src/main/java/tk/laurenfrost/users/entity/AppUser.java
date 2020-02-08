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

    @OneToMany
    @JsonBackReference
    private Set<Contact> contacts;

    @Lob
    private byte[] avatar;

    @JsonManagedReference
    @OneToMany
    private Set<Request> out_request;

    @JsonManagedReference
    @OneToMany
    private Set<Request> in_request;

}