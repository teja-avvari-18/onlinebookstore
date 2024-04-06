package com.cts.onlinebookstore.repository;

import com.cts.onlinebookstore.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart,Long>
{


   List<Cart> findByUserId(Long id);
   void deleteByUserId(Long id);
}
