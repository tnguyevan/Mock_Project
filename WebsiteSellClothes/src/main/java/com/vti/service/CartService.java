package com.vti.service;

import com.vti.entity.Cart;
import com.vti.entity.Pay;
import com.vti.entity.User;
import com.vti.form.creating.CartFormForCreating;
import com.vti.form.creating.PayFormForCreating;
import com.vti.form.updating.CartFormForUpdating;
import com.vti.repository.ICartRepository;
import com.vti.repository.IPayRepository;
import com.vti.repository.IUserRepository;
import com.vti.service.implement.ICartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService implements ICartService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IPayRepository payRepository;

    @Autowired
    private IUserRepository IUserRepository;


    @Override
    public Page<Cart> getCartByUsername(Pageable pageable, String username) {
        return cartRepository.findAllByUserUsername(pageable, username);
    }


    @Override
    public Cart createCart(String username, CartFormForCreating form) {

        User user = IUserRepository.findByUsername(username);

        form.setUserId(user.getId());

        Cart.ShoppingCartKey shoppingCartKey = modelMapper.map(form, Cart.ShoppingCartKey.class);

        // convert form to entity
        Cart cart = modelMapper.map(form, Cart.class);
        cart.setId(shoppingCartKey);

        // update quantity +1 từ màn hình sản phẩm khi bấm thêm giỏ hàng 1 lần nữa
        Cart cartUpdate = cartRepository.findCartByUserIdAndProductId(user.getId(), form.getProductId());

        if (cartUpdate != null) {
            cart.setQuantity(cartUpdate.getQuantity() + 1);
        } else {
            cart.setQuantity(1);
        }
        Cart returnCart = cartRepository.save(cart);
        return returnCart;
    }


    @Override
    public Cart updateQuantityInCart(String username, int productId, CartFormForUpdating form) {
        Cart entity = cartRepository.findCartByUserUsernameAndProductId(username, productId);
        entity.setQuantity(form.getQuantity());
        Cart returnCart = cartRepository.save(entity);
        return returnCart;
    }


    @Override
    public void deleteCartByUsername(String username) {
        cartRepository.deleteCartByUserUsername(username);
    }


    @Override
    public void deleteProductByUsernameAndProductId(String username, int productId) {
        cartRepository.deleteCartByUserUsernameAndProductId(username, productId);
    }


    @Override
    public int total(int userId) {
        PayFormForCreating form = new PayFormForCreating();
        int sum = cartRepository.total(userId);
        form.setUserId(userId);
        form.setTotal(sum);
        Pay pay = modelMapper.map(form, Pay.class);
        payRepository.save(pay);
        return sum;
    }


    @Override
    public boolean existsCartByUserId(int userId) {
        return cartRepository.existsByUserId(userId);
    }


    @Override
    public boolean existsCartByProductId(int productId) {
        return cartRepository.existsByProductId(productId);
    }


}
