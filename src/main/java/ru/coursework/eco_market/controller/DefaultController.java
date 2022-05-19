package ru.coursework.eco_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coursework.eco_market.entity.Product;
import ru.coursework.eco_market.entity.ProductType;
import ru.coursework.eco_market.repository.ProductRepository;
import ru.coursework.eco_market.repository.ProductTypeRepository;
import ru.coursework.eco_market.repository.UserRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// На сервере сделать запрос к базе данных, выдернуть с неё какую-то информацию и передать её на фронт
@Controller
public class DefaultController {

    @Autowired
    ProductTypeRepository productTypeRepository; // Создали доступ к репозиторию

    @Autowired
    ProductRepository productRepository; //Доступ к репозиторию продуктов

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String index(Model model) { // Для открытия страницы index.html при запуске сайта
        Iterable<ProductType> types = productTypeRepository.findAll(); // Используем доступ к репозиторию для выбора всех типов продуктов
        Map<ProductType, List<Product>> map = new HashMap<>();//Для каждого типа продукта будет свой список
        types.forEach(type -> map.put(type, productRepository.findByProductType(type))); // Перебор каждого типа, и для каждого в Map кладём тип (ключ) и значение из ProductRepository
        model.addAttribute("map", map); // Отправляем map на страницу
        model.addAttribute("users", userRepo.findAll());
        return "index";
    }

    @GetMapping("/product")
    public String product(@RequestParam("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        return "cart";
    }
}