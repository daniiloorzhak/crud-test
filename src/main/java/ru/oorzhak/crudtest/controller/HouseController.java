package ru.oorzhak.crudtest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oorzhak.crudtest.dto.HouseCreateAndUpdateDTO;
import ru.oorzhak.crudtest.dto.HouseResponseDTO;
import ru.oorzhak.crudtest.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("houses")
@AllArgsConstructor
@Tag(name = "House")
public class HouseController {
    private final HouseService houseService;

    @GetMapping
    public ResponseEntity<List<HouseResponseDTO>> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody HouseCreateAndUpdateDTO houseDTO) {
        return ResponseEntity.ok(houseService.create(houseDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @Valid @RequestBody HouseCreateAndUpdateDTO houseDTO) {
        return ResponseEntity.ok(houseService.update(id, houseDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(houseService.delete(id));
    }

    @PostMapping("{houseId}")
    public ResponseEntity<Long> addResident(@PathVariable Long houseId, @RequestBody Long userId) {
        return ResponseEntity.ok(houseService.addResident(houseId, userId));
    }
}
