package com.example.design.Controller;


import com.example.design.Model.Rate;
import com.example.design.Service.RateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rate")
public class RateController {

    private final RateService rateService;

    @GetMapping("/get")
    public ResponseEntity getAllRates() {
        return ResponseEntity.ok(rateService.getAllRates());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getRateById(@PathVariable Integer id) {
        Rate rate = rateService.getRateById(id);
        return rate != null ? ResponseEntity.ok(rate) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRate(@RequestBody @Valid Rate rate, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        rateService.addRate(rate);
        return ResponseEntity.status(201).body("Rate added successfully");
    }

//    public ResponseEntity updateRate(@PathVariable Integer id, @RequestBody@Valid Rate rate)

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRate(@PathVariable Integer id) {
        boolean deleted = rateService.deleteRate(id);
        return deleted ? ResponseEntity.ok("Rate deleted successfully") : ResponseEntity.status(404).body("Rate not found");
    }


}
