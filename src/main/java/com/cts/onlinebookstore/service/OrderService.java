package com.cts.onlinebookstore.service;


import com.cts.onlinebookstore.exception.ResourceNotFoundException;
import com.cts.onlinebookstore.model.*;
import com.cts.onlinebookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class OrderService
{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    UserRepository userRepository;

    public List<Order> getAllOrders()
    {
        List<Order> orderList = orderRepository.findAll();
        if(orderList.isEmpty())
        {
            throw new ResourceNotFoundException("No orders are placed yet");
        }
        return orderList;
    }

    public Order getOrderById(Long id)
    {
        try
        {
            Order order = orderRepository.findById(id).get();
            return order;
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Wrong order id : "+id);
        }
    }

//    public Order placeOrder(Order order)
//    {
//        List<Order> orderList = orderRepository.findAll();
//
//        List<Cart> cartList = cartRepository.findByUserId(order.getUser().getId());
//        int quantity = 0;
//        double price = 0;
//
//        for(Cart existingCart:cartList)
//        {
//            quantity+=existingCart.getQuantity();
//            price = existingCart.getPrice()+price;
//        }
//
//        order.setTotalQuantity(quantity);
//        order.setTotalPrice(price);
//
//        order.setUser(userRepository.findById(order.getUser().getId()).get());
//        order.setStatus("Success");
//        Order order1 = orderRepository.save(order);
//
//        for(Cart existingCart:cartList)
//        {
//            OrderItems orderItem = new OrderItems(existingCart.getQuantity(),existingCart.getPrice(),order1,existingCart.getBook());
//
//            Book book = bookRepository.findById(existingCart.getBook().getId()).get();
//            book.setStockAvailable(book.getStockAvailable()-existingCart.getQuantity());
//            bookRepository.save(book);
//            orderItemsRepository.save(orderItem);
//        }
//
//        cartRepository.deleteByUserId(order.getUser().getId());
//        return order1;
//    }


    public Order placeOrder(Long userId)
    {
        User user = userRepository.findById(userId).get();
        List<Order> orderList = orderRepository.findAll();

        Order order = new Order();

        List<Cart> cartList = cartRepository.findByUserId(user.getId());
        int quantity = 0;
        double price = 0;

        for(Cart existingCart:cartList)
        {
            quantity+=existingCart.getQuantity();
            price = existingCart.getPrice()+price;
        }

        order.setTotalQuantity(quantity);
        order.setTotalPrice(price);

        order.setUser(userRepository.findById(userId).get());
        order.setOrderDate(LocalDate.now());
        order.setStatus("Success");
        Order order1 = orderRepository.save(order);

        for(Cart existingCart:cartList)
        {
            OrderItems orderItem = new OrderItems(existingCart.getQuantity(),existingCart.getPrice(),order1,existingCart.getBook());

            Book book = bookRepository.findById(existingCart.getBook().getId()).get();
            book.setStockAvailable(book.getStockAvailable()-existingCart.getQuantity());
            bookRepository.save(book);
            orderItemsRepository.save(orderItem);
        }

        cartRepository.deleteByUserId(userId);
        return order1;
    }

    public String removeOrder(Long id)
    {
        Order order = getOrderById(id);
        order.setStatus("Cancelled");
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        for(OrderItems e : orderItemsList){
            if(e.getOrder().getId()==id){
                Book book = bookRepository.findById(e.getBook().getId()).get();
                book.setStockAvailable(e.getQuantity()+book.getStockAvailable());
            }
        }
        orderRepository.save(order);
        return "Your order has been cancelled successfully";
    }


}
