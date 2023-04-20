package com.shop.shoes.repository;

import com.shop.shoes.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
