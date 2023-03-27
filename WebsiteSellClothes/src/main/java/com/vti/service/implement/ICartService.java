package com.vti.service.implement;

import com.vti.entity.Cart;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.updating.CartFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICartService {

    Page<Cart> getCartByUsername(Pageable pageable, String username);

    Cart createCart(String username, CartFormForCreating form);

    Cart updateQuantityInCart(String username, int productId, CartFormForUpdating form);

    void deleteCartByUsername(String username);

    void deleteProductByUsernameAndProductId(String username, int productId);

    int total(int userId);

    boolean existsCartByUserId(int userId);

    boolean existsCartByProductId(int userId);

}
