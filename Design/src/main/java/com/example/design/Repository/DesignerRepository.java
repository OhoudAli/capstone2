package com.example.design.Repository;


import com.example.design.Model.Designer;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepository extends JpaRepository<Designer,Integer> {

    Designer findDesignerById(Integer id);

    Designer findDesignerByName(String name);

}
