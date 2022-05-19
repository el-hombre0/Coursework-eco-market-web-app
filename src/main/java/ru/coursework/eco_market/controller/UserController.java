package ru.coursework.eco_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coursework.eco_market.entity.Role;
import ru.coursework.eco_market.entity.User;
import ru.coursework.eco_market.repository.UserRepo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')") // Чтобы доступ к изменеию пользователей имел только админ
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}") // Изменение параметров пользователя
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PostMapping // Сохранение изменений параметров пользователя
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ){
        user.setUsername(username); //Установим новое имя
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());// Получаем список ролей для проверки, что они установлены данному пользователю

        user.getRoles().clear(); // Для работы перебора формы ниже

        for (String key : form.keySet()) { //Проверка, что форма содержит роли для данного пользователя
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key)); // Добаляем юзеру роль в список ролей, полученную через valueOf
            }
        }
        userRepo.save(user); //Сохраняем в репозиторий
        return "redirect:/user";
    }
}
