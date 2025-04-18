package com.example.design.Repository;


import com.example.design.Model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate,Integer> {

    Rate findRateById(Integer id);

    List<Rate> findByCustomerId(Integer customerId);

    List<Rate> findByServiceId(Integer serviceId);

}
