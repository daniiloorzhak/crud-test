package ru.oorzhak.crudtest.service;

import ru.oorzhak.crudtest.dto.UserRegisterAndUpdateDTO;
import ru.oorzhak.crudtest.model.User;

import java.util.List;

public interface UserService {
    List<String> getAllUsers();
    Long save(UserRegisterAndUpdateDTO userRegisterAndUpdateDTO);
    Long update(UserRegisterAndUpdateDTO userDTO);
    Long delete();
    User getCurrentUser();
}
