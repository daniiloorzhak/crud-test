package ru.oorzhak.crudtest.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oorzhak.crudtest.dto.UserRegisterAndUpdateDTO;
import ru.oorzhak.crudtest.model.Role;
import ru.oorzhak.crudtest.model.User;
import ru.oorzhak.crudtest.repository.UserRepository;
import ru.oorzhak.crudtest.service.UserService;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<String> getAllUsers() {
        return userRepository.findAll().stream().map(User::getUsername).toList();
    }

    @Override
    public Long save(UserRegisterAndUpdateDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername()))
            throw new RuntimeException();
        return userRepository.save(userRegisterDtoToUser(userDTO)).getId();
    }

    @Override
    public Long update(UserRegisterAndUpdateDTO userDTO) {
        User user = getCurrentUser();
        if (userDTO.getUsername() != null && userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException();
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getAge() != null) {
            user.setAge(userDTO.getAge());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
        return null;
    }

    @Override
    @Transactional
    public Long delete() {
        Long id = getCurrentUser().getId();
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public User getCurrentUser() {
        var authToken = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authToken.getName()).orElseThrow();
    }

    private User userRegisterDtoToUser (UserRegisterAndUpdateDTO dto) {
        return User.builder()
                .username(dto.getUsername())
                .age(dto.getAge())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(Role.USER_ROLE))
                .build();
    }
}
