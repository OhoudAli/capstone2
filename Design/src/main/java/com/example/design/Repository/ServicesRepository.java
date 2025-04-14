package com.example.design.Repository;


import com.example.design.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services,Integer> {

    Services findServicesById(Integer id);

    List<Services> findByDesignerId(Integer designerId);


}
