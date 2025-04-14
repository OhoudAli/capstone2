package com.example.design.Service;


import com.example.design.Model.Rate;
import com.example.design.Repository.CustomerRepository;
import com.example.design.Repository.OrderRepository;
import com.example.design.Repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {


    private final RateRepository rateRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }


    public void addRate(Rate rate) {

        rateRepository.save(rate);
    }

    public boolean deleteRate(Integer id) {
        Rate rate = rateRepository.findRateById(id);
        if (rate == null) {
            return false;
        }
        rateRepository.delete(rate);
        return true;
    }
    public Rate getRateById(Integer id) {
        return rateRepository.findRateById(id);
    }

}
