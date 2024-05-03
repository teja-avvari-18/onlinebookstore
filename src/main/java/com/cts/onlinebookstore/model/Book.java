package com.cts.onlinebookstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "book")
public class
Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private String authorName;
    private String genre;
    private int year;
    private double price;
    private int stockAvailable;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<Cart> cart = new HashSet<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<OrderItems> orderItems = new HashSet<>();
}
