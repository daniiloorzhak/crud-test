package ru.oorzhak.crudtest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oorzhak.crudtest.dto.UserRegisterAndUpdateDTO;
import ru.oorzhak.crudtest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<String>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping
    public ResponseEntity<Long> updateUser(@Valid @RequestBody UserRegisterAndUpdateDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteUser() {
        return ResponseEntity.ok(userService.delete());
    }
}
