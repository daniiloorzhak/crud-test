package ru.oorzhak.crudtest.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterAndUpdateDTO {
    @NotBlank
    private String username;
    @NotNull
    @Min(1)
    @Max(150)
    private Long age;
    @NotBlank
    private String password;
}
