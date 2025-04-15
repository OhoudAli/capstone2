package com.example.design.Controller;


import com.example.design.Model.Order_table;
import com.example.design.Service.OrderService;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Order_table order, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.addOrder(order);
        return ResponseEntity.status(201).body("Order added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id , @RequestBody @Valid Order_table order, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        orderService.updatedOrder(id, order);
        return ResponseEntity.status(200).body("Order updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        boolean deleted = orderService.deleteOrder(id);
        return deleted ? ResponseEntity.ok("Order deleted successfully") : ResponseEntity.status(404).body("Order not found");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getOrderById(@PathVariable Integer id) {
        Order_table order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PutMapping("/updateStatus/{orderId}/{status}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Integer orderId, @PathVariable String status) {
        String response = orderService.updateOrderStatus(orderId, status);
        return response.equals("Order status updated successfully")
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(400).body(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity getOrdersByStatus(@PathVariable String status) {
        List<Order_table> orders = orderService.getOrdersByStatus(status);
        return orders.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.ok(orders);
    }


    @PostMapping("/purchase/{customerId}/{designId}")
    public ResponseEntity purchaseItem(@PathVariable Integer customerId, @PathVariable Integer designId) {

        boolean success = orderService.purchaseItem(customerId, designId);
        if (success) {
            return ResponseEntity.status(200).body("Purchase successful!");
        } else {
            return ResponseEntity.status(400).body("Purchase failed. Insufficient funds or invalid item.");
        }
    }


    @PutMapping("/cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable Integer id) {
        boolean canceled = orderService.cancelOrder(id);
        return canceled ? ResponseEntity.status(200).body("Order canceled successfully") : ResponseEntity.status(400).body("Order not found or cannot be canceled");
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity trackOrder(@PathVariable Integer orderId) {
        String status = orderService.trackOrder(orderId);
        if (status != null) {
            return ResponseEntity.ok("Order status: " + status);
        }
        return ResponseEntity.status(404).body("Order not found");
    }


}
