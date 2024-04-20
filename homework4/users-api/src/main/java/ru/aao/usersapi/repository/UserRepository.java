package ru.aao.usersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aao.usersapi.model.dao.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
}
