package tk.laurenfrost.users.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @Lob
    private String bio;

    private String city;

    private Boolean privateContacts = false;

    private String avatarPath;

}