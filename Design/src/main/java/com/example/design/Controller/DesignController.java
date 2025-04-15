package com.example.design.Controller;


import com.example.design.Model.Design;
import com.example.design.Service.DesignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/design")
public class DesignController {

    private final DesignService designService;

    @GetMapping("/get")
    public ResponseEntity getAllDesigns() {
        return ResponseEntity.ok(designService.getAllDesigns());
    }


    @PostMapping("/add")
    public ResponseEntity<String> addDesign(@RequestBody Design design) {
        designService.addDesign(design);
        return ResponseEntity.status(201).body("Design added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDesign(@PathVariable Integer id) {
        boolean deleted = designService.deleteDesign(id);
        return deleted ? ResponseEntity.ok("Design deleted successfully") : ResponseEntity.status(404).body("Design not found");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getDesignById(@PathVariable Integer id) {
        Design design = designService.getDesignById(id);
        return design != null ? ResponseEntity.ok(design) : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter/{minPrice}/{maxPrice}")
    public ResponseEntity filterCollectionsByPrice(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        List<Design> designs = designService.filterByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(designs);
    }
}
