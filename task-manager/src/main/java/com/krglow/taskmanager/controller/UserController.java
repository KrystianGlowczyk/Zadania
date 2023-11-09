package com.krglow.taskmanager.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krglow.taskmanager.service.user.UserService;
import com.krglow.taskmanager.service.user.dto.UserDto;
import com.krglow.taskmanager.service.user.dto.VUserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/users")
@Tag(name = "Kontroler Użytkownika", description = "Endpoint do zarządzania użytkownikami")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Pobierz wszystkich użytkowników", description = "Pobierz listę wszystkich użytkowników")
    public ResponseEntity<Page<VUserDto>> getAllUsers(@PageableDefault(size = 25) Pageable pageable) {
        Page<VUserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pobierz użytkownika po ID", description = "Pobierz użytkownika po jego ID")
    public ResponseEntity<VUserDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(userService.read(id));
    }

    @PostMapping
    @Operation(summary = "Stwórz użytkownika", description = "Stwórz nowego użytkownika")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Użytkownik został utworzony"), @ApiResponse(responseCode = "400", description = "Błędne żądanie") })
    public ResponseEntity<Long> create(@RequestBody @Valid UserDto dto) {
        Long userId = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aktualizuj użytkownika", description = "Aktualizuj dane użytkownika")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Użytkownik został zaktualizowany"), @ApiResponse(responseCode = "400", description = "Błędne żądanie"),
            @ApiResponse(responseCode = "404", description = "Użytkownik nie został znaleziony") })
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
        Long updatedUserId = userService.update(id, dto);
        if (updatedUserId != null) {
            return ResponseEntity.ok(updatedUserId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Usuń użytkownika", description = "Usuń użytkownika po ID")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Użytkownik został usunięty"), @ApiResponse(responseCode = "404", description = "Użytkownik nie został znaleziony") })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
