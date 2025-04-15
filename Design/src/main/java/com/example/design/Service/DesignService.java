package com.example.design.Service;


import com.example.design.Model.Design;
import com.example.design.Repository.DesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignService {

    private final DesignRepository designRepository;

    public List<Design> getAllDesigns() {
        return designRepository.findAll();
    }


    public void addDesign(Design design) {
        designRepository.save(design);
    }


    public Boolean deleteDesign(Integer id) {
        Design design = designRepository.findDesignById(id);
        if (design == null) {
            return false;
        }
        designRepository.delete(design);
        return true;
    }
    public Design getDesignById(Integer id) {
        return designRepository.findDesignById(id);
    }

    // Filter collections by price range
    public List<Design> filterByPriceRange(Double minPrice, Double maxPrice) {
        return designRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
