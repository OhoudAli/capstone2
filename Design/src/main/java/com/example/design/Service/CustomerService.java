package com.example.design.Service;


import com.example.design.Model.Customer;
import com.example.design.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public Boolean updateCustomer(Integer id, Customer customer){

        Customer oldCustomer = customerRepository.findCustomerById(id);
        if(oldCustomer == null){
            return false;
        }
        oldCustomer.setName(customer.getName());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setBalance(customer.getBalance());
        oldCustomer.setUsername(customer.getUsername());
        oldCustomer.setPassword(customer.getPassword());

        customerRepository.save(oldCustomer);
        return true;

    }
    public Boolean deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            return false;
        }
        customerRepository.delete(customer);
        return true;
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

}
