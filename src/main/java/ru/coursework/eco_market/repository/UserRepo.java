package ru.coursework.eco_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coursework.eco_market.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
