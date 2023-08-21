package ru.oorzhak.crudtest.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oorzhak.crudtest.dto.HouseCreateAndUpdateDTO;
import ru.oorzhak.crudtest.dto.HouseResponseDTO;
import ru.oorzhak.crudtest.dto.UserResponseDTO;
import ru.oorzhak.crudtest.model.House;
import ru.oorzhak.crudtest.model.User;
import ru.oorzhak.crudtest.repository.HouseRepository;
import ru.oorzhak.crudtest.repository.UserRepository;
import ru.oorzhak.crudtest.service.HouseService;
import ru.oorzhak.crudtest.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    @Transactional
    public List<HouseResponseDTO> getAllHouses() {
        return houseRepository.findAll().stream().map(house -> {
            HouseResponseDTO houseDTO = new HouseResponseDTO();
            houseDTO.setId(house.getId());
            houseDTO.setAddress(house.getAddress());
            houseDTO.setOwner(userToUserResponseDTO(house.getOwner()));
            houseDTO.setResidents(userSetToUserResponseDTOSet(house.getResidents()));
            return houseDTO;
        }).toList();
    }

    private UserResponseDTO userToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(userResponseDTO.getUsername());
        return userResponseDTO;
    }

    private Set<UserResponseDTO> userSetToUserResponseDTOSet(Set<User> users) {
        return users.stream().map(user -> {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(userResponseDTO.getId());
            userResponseDTO.setUsername(userResponseDTO.getUsername());
            return userResponseDTO;
        }).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Long create(HouseCreateAndUpdateDTO houseDTO) {
        House house = new House();
        house.setAddress(houseDTO.getAddress());
        house.setOwner(userService.getCurrentUser());
        return houseRepository.save(house).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, HouseCreateAndUpdateDTO houseDTO) {
        House house = houseRepository.findById(id).orElseThrow();
        if (!Objects.equals(house.getOwner().getId(), userService.getCurrentUser().getId()))
            throw new RuntimeException();
        if (houseDTO.getAddress() != null) {
            house.setAddress(houseDTO.getAddress());
        }
        return houseRepository.save(house).getId();
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        House house = houseRepository.findById(id).orElseThrow();
        if (!Objects.equals(house.getOwner().getId(), userService.getCurrentUser().getId()))
            throw new RuntimeException();
        houseRepository.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public Long addResident(Long houseId, Long userId) {
        House house = houseRepository.findById(houseId).orElseThrow();
        if (!Objects.equals(house.getOwner().getId(), userService.getCurrentUser().getId()))
            throw new RuntimeException();
        User resident = userRepository.findById(userId).orElseThrow();
        house.getResidents().add(resident);
        houseRepository.save(house);
        return house.getId();
    }
}
