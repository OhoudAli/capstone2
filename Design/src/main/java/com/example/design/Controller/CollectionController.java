package com.example.design.Controller;


import com.example.design.Api.ApiResponse;
import com.example.design.Model.Collection;
import com.example.design.Service.CollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController {

    private final CollectionService collectionService;


    @GetMapping("/get")
    public ResponseEntity getAllCollections(){
        return ResponseEntity.status(200).body(collectionService.getAllCollections());
    }


    @PostMapping("/add")
    public ResponseEntity addCollection(@RequestBody@Valid Collection collection, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        collectionService.addCollection(collection);
        return ResponseEntity.status(201).body("Collection added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCollection(@PathVariable Integer id) {
        boolean isDeleted = collectionService.deleteCollection(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Collection deleted successfully");
        }
        return ResponseEntity.status(400).body("Collection not found");
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getCollectionById(@PathVariable Integer id) {
        Collection collection = collectionService.getCollectionById(id);
        return collection != null ? ResponseEntity.ok(collection) : ResponseEntity.notFound().build();
    }




    @GetMapping("/get/{collectionId}")
    public ResponseEntity getDesignsByCollection(@PathVariable Integer collectionId) {
        return ResponseEntity.status(200).body(collectionService.getDesignsByCollection(collectionId));
    }

    @GetMapping("/filter/{minPrice}/{maxPrice}")
    public ResponseEntity filterCollectionsByPrice(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        List<Collection> collections = collectionService.filterByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(collections);
    }

}
