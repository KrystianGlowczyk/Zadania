package com.krglow.taskmanager.service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(description = "Model użytkownika")
public class UserDto {

    @Schema(description = "Identyfikator użytkownika")
    private Long id;

    @Schema(description = "Imię użytkownika")
    // @NotNull(message = "Pole 'name' nie może być puste")
    @Size(min = 2, max = 50, message = "Imię użytkownika powinno mieć od 2 do 50 znaków")
    private String name;

    // @NotNull(message = "Pole 'surname' nie może być puste")
    @Size(min = 2, max = 50, message = "Nazwisko użytkownika powinno mieć od 2 do 50 znaków")
    @Schema(description = "Nazwisko użytkownika")
    private String surname;

    @Schema(description = "Email użytkownika")
    // @NotNull(message = "Pole 'email' nie może być puste")
    @Email(message = "Niepoprawny format adresu email")
    private String email;

}
