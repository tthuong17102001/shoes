package com.shop.shoes.repository;

import com.shop.shoes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM products WHERE product_name LIKE %?1%" , nativeQuery = true)
    public List<Product> searchProduct(String productName);
}
