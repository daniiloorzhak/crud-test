package ru.oorzhak.crudtest.service;

import ru.oorzhak.crudtest.dto.HouseCreateAndUpdateDTO;
import ru.oorzhak.crudtest.dto.HouseResponseDTO;

import java.util.List;

public interface HouseService {
    List<HouseResponseDTO> getAllHouses();
    Long create(HouseCreateAndUpdateDTO houseDTO);
    Long update(Long id, HouseCreateAndUpdateDTO houseDTO);
    Long delete(Long id);
    Long addResident(Long houseId, Long userId);
}
