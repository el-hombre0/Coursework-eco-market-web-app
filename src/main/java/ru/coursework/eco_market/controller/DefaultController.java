package ru.coursework.eco_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.coursework.eco_market.entity.ProductType;
import ru.coursework.eco_market.repository.ProductTypeRepository;

// На сервере сделать запрос к базе данных, выдернуть с неё какую-то информацию и передать её на фронт
@Controller
public class DefaultController {

    @Autowired
    ProductTypeRepository productTypeRepository; // Создали доступ к репозиторию

    @GetMapping("/")// Get-запрос
    public String index(Model model){ // Для открытия страницы index.html при запуске сайта
        Iterable<ProductType> types = productTypeRepository.findAll(); // Используем доступ к репозиторию для выбора всех типов продуктов
        model.addAttribute("types", types); // В модели работаем с типами types
        return "index";
    }
}
