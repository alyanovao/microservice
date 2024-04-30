package ru.aao.usersapi.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aao.api.model.User;
import ru.aao.usersapi.model.dao.UserDao;
import ru.aao.usersapi.exception.ApplicationException;
import ru.aao.usersapi.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ConvertorService convertorService;

    @Override
    @Transactional
    public User getUserById(Long id) {
        val user = repository.findById(id).orElseThrow(() -> new ApplicationException("not found user"));
        return convertorService.convertUserDaoTO(user);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        val users = repository.findAll();
        return users.stream().map(convertorService::convertUserDaoTO).toList();
    }

    @Override
    @Transactional
    public Boolean save(User user) {
        val userDao = convertorService.convertUserTo(user);
        repository.save(userDao);
        return true;
    }

    @Override
    @Transactional
    public Boolean update(Long id, User user) {
        val userDao = UserDao.builder()
                        .id(id)
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .build();
        repository.save(userDao);
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        val user = repository.findById(id).orElseThrow(() -> new ApplicationException("not found user"));
        repository.delete(user);
        return true;
    }
}
