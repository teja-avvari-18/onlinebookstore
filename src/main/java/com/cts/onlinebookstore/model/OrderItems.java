package com.cts.onlinebookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItems")
public class OrderItems
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public OrderItems(int quantity, double price, Order order, Book book)
    {
        super();
        this.quantity=quantity;
        this.price=price;
        this.order=order;
        this.book=book;
    }
}
