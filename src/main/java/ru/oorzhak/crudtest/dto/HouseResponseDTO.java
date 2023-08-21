package ru.oorzhak.crudtest.dto;

import lombok.Data;

import java.util.Set;

@Data
public class HouseResponseDTO {
    private Long id;
    private String address;
    private UserResponseDTO owner;
    private Set<UserResponseDTO> residents;
}
