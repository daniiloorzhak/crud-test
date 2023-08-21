package ru.oorzhak.crudtest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oorzhak.crudtest.dto.UserLoginDTO;
import ru.oorzhak.crudtest.dto.UserRegisterAndUpdateDTO;
import ru.oorzhak.crudtest.service.UserService;
import ru.oorzhak.crudtest.util.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for signing in and singing up a user")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("signin")
    public ResponseEntity<Map<String, String>> signin(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        var authentication = authenticationManager.authenticate(authToken);
        String jwtToken = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(Map.of("jwt-token", jwtToken));
    }

    @PostMapping("signup")
    public ResponseEntity<Long> signup(@Valid @RequestBody UserRegisterAndUpdateDTO userRegisterAndUpdateDTO) {
        return ResponseEntity.ok(userService.save(userRegisterAndUpdateDTO));
    }
}
