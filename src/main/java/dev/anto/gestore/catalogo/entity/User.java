package dev.anto.gestore.catalogo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "email", nullable = false, length = 100)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "surname", length = 100)
    private String surname;
}
