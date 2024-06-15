package ru.aao.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.userservice.exception.ApplicationException;
import ru.aao.userservice.model.dto.UserDto;
import ru.aao.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserDto getUserById(long id) {
        return repository.findById(id).orElseThrow(() -> new ApplicationException("not found user"));
    }

    @Override
    @Transactional
    public Boolean save(long id, String login) {
        repository.save(id, login);
        return true;
    }

    @Override
    @Transactional
    public Boolean update(UserDto user) {
        repository.update(user);
        return true;
    }
}
