package com.example.design.Service;


import com.example.design.Model.Customer;
import com.example.design.Model.Design;
import com.example.design.Model.Order_table;
import com.example.design.Repository.CustomerRepository;
import com.example.design.Repository.DesignRepository;
import com.example.design.Repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DesignRepository designRepository;
    private final CustomerRepository customerRepository;

    public List<Order_table> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order_table order) {
        orderRepository.save(order);
    }


    public Boolean updatedOrder(Integer id , Order_table order){
        Order_table oldOrder = orderRepository.findOrderById(id);
        if(oldOrder == null){
            return false;
        }

        oldOrder.setName(order.getName());
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setPrice(order.getPrice());
        oldOrder.setQuantity(order.getQuantity());


        orderRepository.save(oldOrder);
        return true;
    }

    public boolean deleteOrder(Integer id) {
        Order_table order = orderRepository.findOrderById(id);
        if (order == null) {
            return false;
        }
        orderRepository.delete(order);
        return true;
    }

    public Order_table getOrderById(Integer id) {
        return orderRepository.findOrderById(id);
    }


    public String updateOrderStatus(Integer id, String status) {
        Order_table order = orderRepository.findOrderById(id);
        if (order == null) {
            return "Order not found";
        }
        order.setOrderStatus(status);
        orderRepository.save(order);
        return "Order status updated successfully";
    }

    public List<Order_table> getOrdersByStatus(String status) {
        return orderRepository.findOrder_tableByOrderStatus(status);
    }


public Boolean purchaseItem(Integer customerId,Integer designId) {

        Design design = designRepository.findDesignById(designId);
        Customer customer = customerRepository.findCustomerById(customerId);
        if (design == null || customer == null) {
            return false;
        }

        Order_table orderTable = new Order_table();
        orderTable.setCustomer_id(customerId);
        orderTable.setDesign_id(designId);
        orderTable.setPrice(design.getPrice());
    orderTable.setName(orderTable.getName());
    orderTable.setQuantity(orderTable.getQuantity());

    return processPurchase(orderTable);

}


    private Boolean processPurchase(Order_table orderTable) {
        Customer customer = customerRepository.findCustomerById(orderTable.getCustomer_id());
        if (customer == null || customer.getBalance() < orderTable.getPrice()) {
            return false;
        }
            Design design = designRepository.findDesignById(orderTable.getDesign_id());
            if(design == null){
                return false;
            }

        orderRepository.save(orderTable);
        customer.setBalance(customer.getBalance() - orderTable.getPrice());
        customer.setOrderId(orderTable.getId());


        customerRepository.save(customer);

        return true;
    }


    // endpoint to let the customer cancel the order if not want it anymore except if it is not pending
    public Boolean cancelOrder(Integer orderId) {
        Order_table order = orderRepository.findOrderById(orderId);
        if (order == null || !order.getOrderStatus().equals("pending")) {
            return false;
        }
        order.setOrderStatus("canceled");
        orderRepository.save(order);
        return true;
    }


    // To Track the order status
    public String trackOrder(Integer orderId) {
        Order_table order = orderRepository.findOrderById(orderId);
        if (order != null) {
            return order.getOrderStatus();
        }
        return null;
    }


}
