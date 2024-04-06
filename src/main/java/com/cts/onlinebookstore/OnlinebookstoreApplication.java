package com.cts.onlinebookstore;

import com.cts.onlinebookstore.dto.CartDTO;
import com.cts.onlinebookstore.model.*;
import com.cts.onlinebookstore.repository.BookRepository;
import com.cts.onlinebookstore.repository.OrderItemsRepository;
import com.cts.onlinebookstore.service.BookService;
import com.cts.onlinebookstore.service.CartService;
import com.cts.onlinebookstore.service.OrderService;
import com.cts.onlinebookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OnlinebookstoreApplication implements CommandLineRunner
{
	@Autowired
	CartService cartService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemsRepository orderItemsRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args)  {
		SpringApplication.run(OnlinebookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		//Cart cart = new Cart();
		//Book book = bookService.getBookById(2L);
		//User user = userService.getUserById(3L);
//		CartDTO cartDTO = new CartDTO(2,2L,3L);
//		//cart.setQuantity(2);
//		//cart.setBook(bookService.getBookById(3L));
//		//cart.setUser(userService.getUserById(3L));
////		cartService.createCart(cartDTO);
//		Book book = bookService.getBookById(2L);
//		book.setPrice(100.50);
//		bookService.updateBook(book);
//		Order order = orderService.getOrderById(2L);
//		Set<OrderItems> orderList = order.getOrderItems();
//		for (OrderItems e: orderList){
//			System.out.println(e);
//		}

	}
}
