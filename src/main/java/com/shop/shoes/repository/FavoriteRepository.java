package com.shop.shoes.repository;

import com.shop.shoes.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    //favorites
    @Query(value = "select * from favorites where product_id=? and user_id=?;",nativeQuery = true)
    public Favorite selectSaves(Long productId,Long userId);
    // ds favorite cua 1 nguoi
    @Query(value = "SELECT * FROM favorites where user_id = ?;", nativeQuery = true)
    public List<Favorite> selectAllSaves(Long userId);
    //dem
    @Query(value = "SELECT Count(favorite_id)  FROM favorites  where user_id = ?;", nativeQuery = true)
    public Integer selectCountSave(Long userId);
}
