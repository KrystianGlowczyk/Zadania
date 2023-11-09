package com.krglow.taskmanager.controller;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krglow.taskmanager.service.task.TaskService;
import com.krglow.taskmanager.service.task.dto.TaskDto;
import com.krglow.taskmanager.service.task.dto.VTaskDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
@Tag(name = "Kontroler zadania", description = "Endpoint do zarządzania zadaniami")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Zwraca listę zadań")
    public ResponseEntity<Page<VTaskDto>> find(@PageableDefault(size = 25) Pageable pageable) {
        Page<VTaskDto> tasks = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Zwraca zadanie o podanym ID")
    public ResponseEntity<VTaskDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.read(id));
    }

    @PostMapping
    @Operation(summary = "Tworzy nowe zadanie")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "Zadanie zostało utworzone"), @ApiResponse(responseCode = "400", description = "Błąd w żądaniu") })
    public ResponseEntity<Long> create(@RequestBody @Valid TaskDto dto) {
        Long taskId = taskService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aktualizuje zadanie")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Zadanie zostało zaktualizowane"), @ApiResponse(responseCode = "400", description = "Błąd w żądaniu"),
            @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody @Valid TaskDto dto) {
        Long updatedTaskId = taskService.update(id, dto);
        if (updatedTaskId != null) {
            return ResponseEntity.ok(updatedTaskId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Usuwa zadanie")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Zadanie zostało usunięte"), @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Zmienia status zadania")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Status zadania został zmieniony"), @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<HttpStatus> changeStatus(@PathVariable Long id, @RequestBody Long statusId) {
        taskService.changeStatus(statusId, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/assignUsers")
    @Operation(summary = "Przypisuje użytkowników do zadania")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Użytkownicy zostali przypisani do zadania"), @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<HttpStatus> assignUsers(@PathVariable Long id, @RequestBody Set<Long> userIds) {
        taskService.assignUsers(id, userIds);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/assignUser")
    @Operation(summary = "Przypisuje użytkownika do zadania")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Użytkownik został przypisany do zadania"), @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<HttpStatus> assignUser(@PathVariable Long id, @RequestBody Long userId) {
        taskService.assignUserToTask(id, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}/removeUser")
    @Operation(summary = "Usuwa użytkownika z zadania")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "Użytkownik został usunięty z zadania"), @ApiResponse(responseCode = "404", description = "Zadanie nie znalezione") })
    public ResponseEntity<HttpStatus> removeUserFromTask(@PathVariable Long id, @RequestBody Long userId) {
        taskService.removeUserFromTask(id, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
