package ru.aao.userauthservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.userauthservice.model.dto.UserDto;
import ru.aao.userauthservice.repository.UserAuthRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserAuthService {

    private final UserAuthRepository repository;

    @Override
    @Transactional
    public Optional<UserDto> findByLoginPassword(String login, String password) {
        return repository.findByLoginPassword(login, password);
    }

    @Override
    @Transactional
    public UserDto create(UserDto user) {
        return repository.save(user);
    }
}
