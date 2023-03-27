package com.vti.repository;

import com.vti.entity.OderDetailDBConvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderDetailConvertRepository extends JpaRepository<OderDetailDBConvert, Integer> {


    @Query(value = "SELECT  p.productId as oderDetailId,od.oderId,p.productName, p.salePrice, c.quantity ,(p.salePrice * c.quantity) as total\n" +
            "             FROM cart c\n" +
            "            JOIN product p\n" +
            "            USING (productId)\n" +
            "            JOIN oderlist od\n" +
            "            USING (userId)\n" +
            "            WHERE od.oderId = :idParameter", nativeQuery = true)
    List<OderDetailDBConvert> getOderDetailByOderId(@Param("idParameter") int oderId);


}