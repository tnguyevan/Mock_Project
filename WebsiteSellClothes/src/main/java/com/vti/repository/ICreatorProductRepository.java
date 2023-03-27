package com.vti.repository;

import com.vti.entity.CreatorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ICreatorProductRepository extends JpaRepository<CreatorProduct, Integer> {

    List<CreatorProduct> findCreatorProductsByUserUsername(String username);

    @Modifying
    void deleteCreatorProductByProductId(int id);


    boolean existsCreatorProductByUserUsernameAndProductId(String username, Integer productId);



}
