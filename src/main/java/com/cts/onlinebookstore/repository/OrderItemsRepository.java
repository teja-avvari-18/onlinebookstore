package com.cts.onlinebookstore.repository;

import com.cts.onlinebookstore.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long>
{

}
