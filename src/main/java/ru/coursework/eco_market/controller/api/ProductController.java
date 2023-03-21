package ru.coursework.eco_market.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.coursework.eco_market.entity.Product;
import ru.coursework.eco_market.repository.ProductRepository;
import ru.coursework.eco_market.repository.ProductTypeRepository;
import ru.coursework.eco_market.repository.UserRepo;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductTypeRepository productTypeRepository; // Создали доступ к репозиторию

    @Autowired
    ProductRepository productRepository; //Доступ к репозиторию продуктов


    @GetMapping
    public String list(){
        Product product = productRepository.findById(id).orElse(null);

        return "index";
    }
}