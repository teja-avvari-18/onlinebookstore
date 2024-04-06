package com.cts.onlinebookstore.service;

import com.cts.onlinebookstore.dto.CartDTO;
import com.cts.onlinebookstore.dto.UpdateCart;
import com.cts.onlinebookstore.exception.ResourceNotFoundException;
import com.cts.onlinebookstore.model.Book;
import com.cts.onlinebookstore.model.Cart;
import com.cts.onlinebookstore.model.User;
import com.cts.onlinebookstore.repository.BookRepository;
import com.cts.onlinebookstore.repository.CartRepository;
import com.cts.onlinebookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

   public List<Cart> getAllCarts()
   {
       List<Cart> cartList = cartRepository.findAll();
       if(cartList.isEmpty())
       {
           throw new ResourceNotFoundException("No items are selected");
       }
       return cartList;
   }

//   public Cart createCart(Cart cartObj)
//   {
//       try
//       {
//           List<Cart> cartList = cartRepository.findAll();
//
//           if(cartList!=null)
//           {
//               for(Cart existingCart: cartList)
//               {
//                   if(existingCart.getUser().getId() == cartObj.getUser().getId())
//                   {
//                       if(existingCart.getBook().getId() == cartObj.getBook().getId())
//                       {
//                           double price = existingCart.getPrice();
//
//                           Book book = bookRepository.findById(cartObj.getBook().getId()).get();
//                           double bookPrice = book.getPrice();
//                           existingCart.setPrice((bookPrice*cartObj.getQuantity())+price);
//                           existingCart.setQuantity(cartObj.getQuantity()+existingCart.getQuantity());
//                           return cartRepository.save(existingCart);
//                       }
//                   }
//               }
//           }

    public Cart createCart(CartDTO cartdto) {

try{
        Book book = bookRepository.findById(cartdto.getBookId()).get();
        User user = userRepository.findById(cartdto.getUserId()).get();
        Cart cartObj = new Cart(cartdto.getQuantity(), user, book);

        List<Cart> cartList = cartRepository.findAll();

        if (cartList != null) {
            for (Cart existingCart : cartList) {
                if (existingCart.getUser().getId() == cartObj.getUser().getId()) {
                    if (existingCart.getBook().getId() == cartObj.getBook().getId()) {
                        double price = existingCart.getPrice();

//                            Book book = bookRepository.findById(cartObj.getBook().getId()).get();
                        double bookPrice = book.getPrice();
                        existingCart.setPrice((bookPrice * cartObj.getQuantity()) + price);
                        existingCart.setQuantity(cartObj.getQuantity() + existingCart.getQuantity());
                        return cartRepository.save(existingCart);
                    }
                }
            }
        }

//           Book book = bookRepository.findById(cartObj.getBook().getId()).get();
        double price = book.getPrice();
        cartObj.setPrice(price * cartObj.getQuantity());

        cartObj.setBook(book);
        cartObj.setUser(userRepository.findById(cartObj.getUser().getId()).get());
        cartObj.setUser(user);
        Cart cart = cartRepository.save(cartObj);

        return cart;

    }catch (Exception e)
       {
           throw  new ResourceNotFoundException("The Selected book does not exist in the database");
       }
   }

   public void removeCart(Long id)
   {
       try
       {
           Cart cart = cartRepository.findById(id).get();
           cartRepository.deleteById(id);
       }
       catch (Exception e)
       {
           throw new ResourceNotFoundException("Something went wrong");
       }
   }

   public Cart updateCart(Long id, UpdateCart updateCart)
   {
        try {
            Cart cartObj = cartRepository.findById(id).get();
            Book book = bookRepository.findById(updateCart.getBookId()).get();
            cartObj.setQuantity(updateCart.getQuantity());
            double bookPrice = book.getPrice();
            cartObj.setBook(book);
            cartObj.setPrice(bookPrice * updateCart.getQuantity());
            return cartRepository.save(cartObj);
        }catch (Exception e){
           throw new ResourceNotFoundException("Check your input");
        }
   }

}
