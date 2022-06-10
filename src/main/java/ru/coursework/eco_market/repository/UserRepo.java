package ru.coursework.eco_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coursework.eco_market.entity.User;
// Репозиторий для списка пользователей для авторизации и регистрации
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username); // Метод, возвращающий пользователя
}
