package com.shop.shoes.repository;

import com.shop.shoes.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // count quantity by product
    @Query(value = "SELECT c.category_id,c.category_name,c.category_image,\r\n"
            + "COUNT(*) AS SoLuong\r\n"
            + "FROM products p\r\n"
            + "JOIN categories c ON p.category_id = c.category_id\r\n"
            + "GROUP BY c.category_id;" , nativeQuery = true)
    List<Object[]> listCategoryByProductName();
    // List product by category
    @Query(value = "SELECT * FROM products WHERE category_id = ?", nativeQuery = true)
    public List<Product> listProductByCategory(Long categoryId);
    // Search Products
    @Query(value = "SELECT * FROM products WHERE product_name LIKE %?1%" , nativeQuery = true)
    public List<Product> searchProduct(String productName);
}
