package dev.anto.gestore.catalogo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(min = 3, max = 100)
    private String name;

    @Size(min = 3, max = 100)
    private String surname;

    @Size(min = 6, max = 100)
    private String password;

}
