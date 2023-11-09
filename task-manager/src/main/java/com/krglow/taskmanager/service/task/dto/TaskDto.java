package com.krglow.taskmanager.service.task.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(description = "Model zadania")
public class TaskDto {

    @Schema(description = "Identyfikator zadania")
    private Long id;

    @Schema(description = "Tytuł zadania")
    @NotNull(message = "Pole 'title' nie może być puste")
    @Size(min = 2, max = 255, message = "Tytuł zadania powinien mieć od 2 do 255 znaków")
    private String title;

    @Schema(description = "Opis zadania")
    @Size(max = 1000, message = "Opis zadania nie może przekraczać 1000 znaków")
    private String description;

    @Schema(description = "Identyfikator statusu")
    @Min(value = 1, message = "Identyfikator statusu musi być większy lub równy 1")
    @Max(value = 4, message = "Identyfikator statusu musi być mniejszy lub równy 4")
    private Long statusId;

    @Schema(description = "Termin wykonania zadania")
    @FutureOrPresent(message = "Termin wykonania zadania musi być w przyszłości lub teraz")
    private LocalDateTime executeTime;

}
