package ru.coursework.eco_market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.coursework.eco_market.entity.Product;
import ru.coursework.eco_market.entity.ProductType;
import ru.coursework.eco_market.repository.ProductRepository;
import ru.coursework.eco_market.repository.ProductTypeRepository;

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

    @GetMapping("/")// Get-запрос
    public String index(Model model) { // Для открытия страницы index.html при запуске сайта
        Iterable<ProductType> types = productTypeRepository.findAll(); // Используем доступ к репозиторию для выбора всех типов продуктов
        Map<ProductType, List<Product>> map = new HashMap<>();//Для каждого типа продукта будет свой список
        types.forEach(type -> map.put(type, productRepository.findByProductType(type))); // Перебор каждого типа, и для каждого в Map кладём тип (ключ) и значение из ProductRepository
        model.addAttribute("map", map); // Отправляем map на страницу
        return "index";
    }

    @GetMapping("/product")
    public String product(@RequestParam("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/productTypeList")
    public String productTypeList(Model model) {
        Iterable<ProductType> types = productTypeRepository.findAll();
        model.addAttribute("types", types);
        return "productTypeList";
    }

    @GetMapping("productTypeList/add")
    public String productTypeListAdd(Model model) {
        ProductType productType = new ProductType();
        model.addAttribute("productType", productType);
        return "productTypeForm";
    }
    @PostMapping("productTypeList/add")
    public String productTypeListAddSubmit(@ModelAttribute ProductType productType, Model model){
        productTypeRepository.save(productType);
        model.addAttribute("types", productTypeRepository.findAll());
        return "productTypeList";
    }

    @GetMapping("/productTypeList/delete/{productTypeId}")
    public String productTypeListDelete(@PathVariable("productTypeId") long id, Model model) {
        productTypeRepository.deleteById(id);
        model.addAttribute("types", productTypeRepository.findAll());
        return "productTypeList";
    }

    @GetMapping("/productTypeList/edit/{productTypeId}")
    public String productTypeListEdit(@PathVariable("productTypeId") long id, Model model) {
        ProductType productType = productTypeRepository.findById(id).orElse(null);
        model.addAttribute("productType", productType);
        return "productTypeForm";
    }

    ////////////////////////////
    @GetMapping("/productList")
    public String productList(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("productList/add")
    public String productListAdd(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "productForm";
    }

    @PostMapping("productList/add")
    public String productListAddSubmit(@ModelAttribute Product product, Model model){
        productRepository.save(product);
        model.addAttribute("product", productRepository.findAll());
        return "productList";
    }

    @GetMapping("/productList/delete/{productId}")
    public String productListDelete(@PathVariable("productId") long id, Model model) {
        productRepository.deleteById(id);
        model.addAttribute("products", productRepository.findAll());
        return "productList";
    }

    @GetMapping("/productList/edit/{productId}")
    public String productListEdit(@PathVariable("productId") long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "productForm";
    }
    @GetMapping("/cart")
    public String cart(Model model){
        return "cart";
    }
}