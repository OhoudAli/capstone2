package com.example.design.Controller;


import com.example.design.Model.Customer;
import com.example.design.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer,Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        customerService.addCustomer(customer);
        return ResponseEntity.status(201).body("Customer added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id , @RequestBody @Valid Customer customer, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        customerService.updateCustomer(id,customer);
        return ResponseEntity.status(200).body("Customer updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        boolean deleted = customerService.deleteCustomer(id);
        return deleted ? ResponseEntity.ok("Customer deleted successfully") : ResponseEntity.status(404).body("Customer not found");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        if(customer== null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(customer) ;
    }


}
