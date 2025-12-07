package com.example.java_spring_mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.java_spring_mvc.domain.Cart;
import com.example.java_spring_mvc.domain.CartDetail;
import com.example.java_spring_mvc.domain.Product;
import com.example.java_spring_mvc.domain.User;
import com.example.java_spring_mvc.repository.CartDetailRepository;
import com.example.java_spring_mvc.repository.CartRepository;
import com.example.java_spring_mvc.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    // public void handleAddProductToCart(String email, long productId) {
    // User user = this.userService.getUserByEmail(email);
    // if (user != null) {
    // Cart cart = this.cartRepository.findByUser(user);

    // if (cart == null) {
    // Cart otherCart = new Cart();
    // otherCart.setUser(user);
    // otherCart.setSum(1);

    // cart = this.cartRepository.save(otherCart);

    // }
    // Product productOptional = this.productRepository.findById(productId);
    // if (productOptional != null) {
    // Product realProduct = productOptional;
    // CartDetail cd = new CartDetail();
    // cd.setCart(cart);
    // cd.setProduct(realProduct);
    // cd.setPrice(realProduct.getPrice());
    // cd.setQuantity(1);
    // this.cartDetailRepository.save(cd);
    // }

    // }
    // }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        User user = userService.getUserByEmail(email);
        if (user == null)
            return;

        Cart cart = cartRepository.findByUser(user);

        // Nếu cart chưa tồn tại -> tạo mới
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setSum(0);
            cart = cartRepository.save(cart);
        }

        Product realProduct = productRepository.findById(productId);
        if (realProduct == null)
            return;

        // Kiểm tra sản phẩm đã trong cart chưa
        CartDetail cd = cartDetailRepository.findByCartAndProduct(cart, realProduct);

        if (cd != null) {
            cd.setQuantity(cd.getQuantity() + 1);
        } else {
            cd = new CartDetail();
            cd.setCart(cart);
            cd.setProduct(realProduct);
            cd.setPrice(realProduct.getPrice());
            cd.setQuantity(1);

            int s = cart.getSum() + 1;
            cart.setSum(s);
            session.setAttribute("cartSum", s);
        }

        cartDetailRepository.save(cd);
    }

    public Cart findCartByUserId(long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        return cartRepository.findByUser(user);
    }

    public void deleteCartDetailById(long id, HttpSession session) {

        Optional<CartDetail> cartdetail = cartDetailRepository.findById(id);
        if (cartdetail.isPresent()) {
            CartDetail cd = cartdetail.get();
            Cart cart = cd.getCart();
            if (cart.getSum() > 1) {
                int s = cart.getSum() - 1;
                cart.setSum(s);
                session.setAttribute("cartSum", s);
                this.cartRepository.save(cart);
            } else {
                cart.setSum(0);
                session.setAttribute("cartSum", 0);
                this.cartRepository.save(cart);
            }
            cartDetailRepository.deleteById(id);
        }

    }
}
