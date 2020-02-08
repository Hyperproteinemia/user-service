package tk.laurenfrost.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("true")
    private boolean privateContacts;

    @Lob
    private byte[] avatar;

}