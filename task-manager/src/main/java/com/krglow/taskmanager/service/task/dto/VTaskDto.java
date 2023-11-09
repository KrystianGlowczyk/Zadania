package com.krglow.taskmanager.service.task.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Sczegóły zadania")
public class VTaskDto {

    @Schema(description = "Identyfikator zadania")
    private Long id;

    @Schema(description = "Tytuł zadania")
    private String title;

    @Schema(description = "Opis zadania")
    private String description;

    @Schema(description = "Identyfikator statusu")
    private Long statusId;

    @Schema(description = "Nazwa statusu")
    private String statusName;

    @Schema(description = "Termin wykonania zadania")
    private LocalDateTime executeTime;

    @Schema(description = "Przypisani użytkownicy do zadania")
    private String assignedUsers;

}
