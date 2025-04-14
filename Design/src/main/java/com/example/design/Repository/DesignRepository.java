package com.example.design.Repository;


import com.example.design.Model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository  extends JpaRepository<Design,Integer> {


    Design findDesignById(Integer id);
}
