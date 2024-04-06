package com.cts.onlinebookstore.dto;

import lombok.Data;

@Data
public class UpdateCart {
    private int quantity;
    private Long bookId;
}
