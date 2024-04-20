package ru.aao.usersapi.service;

import ru.aao.api.model.User;
import ru.aao.usersapi.model.dao.UserDao;

public interface ConvertorService {
    UserDao convertUserTo(User user);
    User convertUserDaoTO(UserDao userDao);
}
