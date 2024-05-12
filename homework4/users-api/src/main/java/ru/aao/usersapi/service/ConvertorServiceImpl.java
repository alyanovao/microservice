package ru.aao.usersapi.service;

import org.springframework.stereotype.Service;
import ru.aao.api.model.User;
import ru.aao.usersapi.model.dao.UserDao;

@Service
public class ConvertorServiceImpl implements ConvertorService {
    @Override
    public UserDao convertUserTo(User user) {
        return UserDao.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public User convertUserDaoTO(UserDao userDao) {
        return new User(userDao.getId(), userDao.getUsername(), userDao.getFirstName(), userDao.getLastName(), userDao.getEmail(), userDao.getPhone());
    }
}
