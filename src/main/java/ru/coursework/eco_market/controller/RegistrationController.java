package ru.coursework.eco_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.coursework.eco_market.entity.Role;
import ru.coursework.eco_market.entity.User;
import ru.coursework.eco_market.repository.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo; // Репозиторий со списком пользователей

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    } // Возвращает view для регистрации
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){ // Map для модели для вывода сообщения
        User userFromDb = userRepo.findByUsername(user.getUsername()); // Объявление БД

        if (userFromDb != null){ // Если пользователь есть в БД, то есть уже зарегистрирован, то сообщаем об этом через модель
            model.put("message", "User exists!"); // Сообщение для пользователя
            return "registration";
        }
        // Обработка нового пользователя
        user.setActive(true); // Новый и сразу активный пользователь
        user.setRoles(Collections.singleton(Role.USER)); // Тк на вход ожидается коллекция в виде set, а у нас только одно
        // значение, то используем шаблон, создающий set с одним значением
        userRepo.save(user); // Сохранение состояния пользователя
        return "redirect:/login"; // При успешной авторизации переброска на страницу логина
    }
}
