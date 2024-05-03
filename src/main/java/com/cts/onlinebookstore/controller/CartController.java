package com.cts.onlinebookstore.controller;

import com.cts.onlinebookstore.dto.CartDTO;
import com.cts.onlinebookstore.dto.UpdateCart;
import com.cts.onlinebookstore.model.Cart;
import com.cts.onlinebookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:4200/",maxAge = 3600)
@RestController
@RequestMapping("/carts")
public class CartController
{

    @Autowired
    private CartService cartService;


    @GetMapping("/allcarts")
    public ResponseEntity<List<Cart>> getAllCarts()
    {
        List<Cart> cartList = cartService.getAllCarts();
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartObj)
    {
        Cart cart = cartService.createCart(cartObj);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void removeCartItem(@PathVariable Long id)
    {
        cartService.removeCart(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id,@RequestBody UpdateCart updateCart)
    {
        Cart cartObj = cartService.updateCart(id,updateCart);
        return new ResponseEntity<>(cartObj,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCartsByUserId(@PathVariable Long userId)
    {
        List<Cart> carts = cartService.getCartsByUserId(userId);
        return new ResponseEntity<>(carts,HttpStatus.OK);
    }
}
