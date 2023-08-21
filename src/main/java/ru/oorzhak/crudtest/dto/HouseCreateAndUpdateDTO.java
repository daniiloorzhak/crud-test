package ru.oorzhak.crudtest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HouseCreateAndUpdateDTO {
    @NotBlank
    private String address;
}
