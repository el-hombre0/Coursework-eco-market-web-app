package ru.coursework.eco_market.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.coursework.eco_market.entity.ProductType;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> { // DAO, CRUD - create, read, update, delete
}
