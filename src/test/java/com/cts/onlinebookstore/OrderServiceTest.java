package com.cts.onlinebookstore;




import com.cts.onlinebookstore.dto.CartDTO;
import com.cts.onlinebookstore.exception.ResourceNotFoundException;
import com.cts.onlinebookstore.model.*;
import com.cts.onlinebookstore.repository.*;
import com.cts.onlinebookstore.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderItems orderItem;
    private User user;
    private Book book;
    private List<Cart> cartList;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);

        book = new Book();
        book.setId(1L);
        book.setStockAvailable(10);

        order = new Order();
        order.setId(1L);
        order.setTotalQuantity(5);
        order.setTotalPrice(50.0);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus("Success");

        orderItem = new OrderItems();
        orderItem.setQuantity(2);
        orderItem.setPrice(10.0);
        orderItem.setOrder(order);
        orderItem.setBook(book);

        cartList = new ArrayList<>();
        cartList.add(new Cart(2, user, book));
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> result = orderService.getAllOrders();

        assertEquals(List.of(order), result);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetAllOrdersEmpty() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getAllOrders());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(1L);

        assertEquals(order, result);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.getOrderById(1L));
        verify(orderRepository, times(1)).findById(1L);
    }



    @Test
    void testRemoveOrder() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderItemsRepository.findAll()).thenReturn(List.of(orderItem));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        String result = orderService.removeOrder(1L);

        assertEquals("Your order has been cancelled successfully", result);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderItemsRepository, times(1)).findAll();
        verify(bookRepository, times(1)).findById(1L);
    }


}