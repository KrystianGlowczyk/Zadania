package com.krglow.taskmanager.service.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Sczegóły użytkownika")
public class VUserDto {

    @Schema(description = "Identyfikator użytkownika")
    private Long id;

    @Schema(description = "Imię użytkownika")
    private String name;

    @Schema(description = "Nazwisko użytkownika")
    private String surname;

    @Schema(description = "Email użytkownika")
    private String email;

}
