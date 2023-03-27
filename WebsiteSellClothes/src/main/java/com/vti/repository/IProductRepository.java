package com.vti.repository;

import com.vti.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    @Modifying
    void deleteByIdIn(List<Integer> ids);

    Page<Product> findProductByCatalogId(Pageable pageable, int catalogId);

    @Query(value = "SELECT * FROM PRODUCT\n" +
            "WHERE CATALOGID = :IdParameter\n" +
            "ORDER BY UPDATEDATE DESC\n" +
            "LIMIT 9 ", nativeQuery = true)
    List<Product> getProductByCatalogId(@Param("IdParameter") int catalogId);

    @Query(value = "SELECT * FROM PRODUCT\n" +
            "ORDER BY UPDATEDATE DESC\n" +
            "LIMIT 5 ", nativeQuery = true)
    List<Product> getProduct();

    boolean existsProductByCatalogId(int id);

}
