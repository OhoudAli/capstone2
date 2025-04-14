package com.example.design.Repository;


import com.example.design.Model.Order_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order_table,Integer> {

    Order_table findOrderById(Integer id);

    List<Order_table> findOrder_tableByOrderStatus(String order_status);
}
