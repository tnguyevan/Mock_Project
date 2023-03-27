package com.vti.repository;

import com.vti.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICartRepository extends JpaRepository<Cart, Cart.ShoppingCartKey> {

    //getProductByUserId
    Page<Cart> findAllByUserUsername(Pageable pageable, String username);


    Cart findCartByUserUsernameAndProductId(String username, int productId);

    //getProductByProductIdAndUserId
    Cart findCartByUserIdAndProductId(int userId, int productId);

    //  delete cart
//    @Modifying
//    @Query(value = "DELETE FROM CART WHERE userId = :idParameter", nativeQuery = true)
    @Modifying
    void deleteCartByUserId(int userId);

    @Modifying
    void deleteCartByUserUsername(String username);


    // delete product in cart
//    @Modifying
//    @Query(value = "DELETE FROM CART WHERE productId = :proIdParameter AND userId = :useIdParameter", nativeQuery = true)
//    void deleteProductInCartByProductId(@Param("proIdParameter") int productId, @Param("useIdParameter") int userId);

    @Modifying
    void deleteCartByUserUsernameAndProductId(String username, int productId);

    // tính tổng
    @Query(value = "SELECT Sum(p.salePrice * c.quantity) as total FROM PRODUCT p\n" +
            "JOIN CART c\n" +
            "USING (productId)\n" +
            "WHERE c.userId = :useIdParameter", nativeQuery = true)
    int total(@Param("useIdParameter") int userId);

    boolean existsByUserId(int userId);

    boolean existsByProductId(int productId);
}
