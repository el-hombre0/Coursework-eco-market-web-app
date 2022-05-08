package ru.coursework.eco_market.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.coursework.eco_market.entity.Product;
import ru.coursework.eco_market.entity.ProductType;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByProductType(ProductType productType); // Находит все продукты данного типа

}
