package com.cts.onlinebookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO
{
    private int quantity;
    private Long bookId;
    private Long userId;
}
