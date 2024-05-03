package com.cts.onlinebookstore.controller;

import com.cts.onlinebookstore.dto.UpdateCart;
import com.cts.onlinebookstore.model.Book;
import com.cts.onlinebookstore.model.Cart;
import com.cts.onlinebookstore.model.Order;
import com.cts.onlinebookstore.model.User;
import com.cts.onlinebookstore.service.OrderService;
import com.cts.onlinebookstore.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:4200/",maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        List<Order> orderList = orderService.getAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id)
    {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public Order createOrder(@RequestBody Order orderObj)
//    {
//        Order order = orderService.placeOrder(orderObj);
//        User user = userService.getUserById(order.getUser().getId());
//        user.setTotalOrder((user.getTotalOrder()!=0 ? user.getTotalOrder():0)+1);
//        userService.updateUser(user);
//        return order;
//    }

    @GetMapping("/add/{id}")
    public Order createOrder(@PathVariable Long id)
    {
        Order order = orderService.placeOrder(id);
        User user = userService.getUserById(id);
        user.setTotalOrder((user.getTotalOrder()!=0 ? user.getTotalOrder():0)+1);
        userService.updateUser(user);
        return order;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id)
    {
        return orderService.removeOrder(id);
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId)
    {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


}
