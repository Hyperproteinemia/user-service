package tk.laurenfrost.users.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

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

    private String bio;

    private String city;

    @ColumnDefault("true")
    private Boolean privateContacts;

    @Lob
    private byte[] avatar;

}