package com.example.design.Controller;


import com.example.design.Model.Rate;
import com.example.design.Model.Services;
import com.example.design.Service.DesignerService;
import com.example.design.Service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class ServicesController {

    private final ServicesService servicesService;
    private final DesignerService designerService;

    @GetMapping("/get")
    public ResponseEntity getAllServices() {
        return ResponseEntity.ok(servicesService.getAllServices());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Services> getServiceById(@PathVariable Integer id) {
        Services service = servicesService.getServiceById(id);
        return service != null ? ResponseEntity.ok(service) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addService(@RequestBody @Valid Services service, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        servicesService.addService(service);
        return ResponseEntity.status(201).body("Service added successfully");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateService(@PathVariable Integer id , @RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        servicesService.updateService(id, services);
        return ResponseEntity.status(200).body("Service updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Integer id) {
        boolean deleted = servicesService.deleteService(id);
        return deleted ? ResponseEntity.ok("Service deleted successfully") : ResponseEntity.status(404).body("Service not found");
    }
//    @PostMapping("/request-shopping-assistance/{customerId}/{designerId}")
//    public ResponseEntity requestShoppingAssistance(@PathVariable Integer customerId, @PathVariable Integer designerId, @RequestBody@Valid String details, Errors errors) {
//        if(errors.hasErrors()){
//            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
//        }
//        servicesService.requestShoppingAssistance(customerId, designerId,details);
//        return ResponseEntity.status(200).body("Shopping assistance request sent.");
//    }

//worked:)   1    but scheck the logic
    @PostMapping("/request-shopping-assistance")
    public ResponseEntity requestShoppingAssistance(@RequestBody @Valid Services services, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        servicesService.requestShoppingAssistance(services);
        return ResponseEntity.status(200).body("Shopping assistance request sent.");
    }

    //worked:) 2 but check the logic
    @PostMapping("/approve-help/{serviceId}/{designerId}")
    public ResponseEntity approveHelpRequest(@PathVariable Integer serviceId, @PathVariable Integer designerId) {
        boolean isApproved = servicesService.approveAssistanceRequest(serviceId, designerId);
        if (isApproved) {
            return ResponseEntity.status(200).body("request approved.");
        } else {
            return ResponseEntity.status(400).body("Failed to approve. Only the designer can approve.");
        }
    }

    // worked :) 3  but check the logic
    @PutMapping("/reject-help/{serviceId}/{designerId}")
    public ResponseEntity rejectHelp(@PathVariable Integer serviceId,@PathVariable Integer designerId) {
        String isRejected = servicesService.rejectHelp(serviceId,designerId);
        if(isRejected!= null){
            return ResponseEntity.status(200).body("Request rejected");
        }
        return ResponseEntity.status(400).body("Failed to reject. Only the designer can approve.");
    }


    @GetMapping("/{serviceId}/status")
    public ResponseEntity getServiceStatus(@PathVariable Integer serviceId) {
        String status = servicesService.getServiceStatus(serviceId);
        if(status.equals("Service not found")){
            return ResponseEntity.status(400).body("Service not found");
        }
        return ResponseEntity.status(200).body(servicesService.getServiceStatus(serviceId));
    }

    @GetMapping("/designer/{designerId}/requests")
    public ResponseEntity getRequestsForDesigner(@PathVariable Integer designerId) {
        return ResponseEntity.status(200).body(servicesService.getRequestsForDesigner(designerId));
    }

    @GetMapping("/designer/{designerId}/services")
    public ResponseEntity getServicesForDesigner(@PathVariable Integer designerId) {
        List<Services> services = servicesService.getServicesForDesigner(designerId);
        if (services.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(services);
    }



    @PostMapping("/create-collaboration")
    public ResponseEntity createCollaboration(@RequestBody @Valid Services services,Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        designerService.createCollaboration(services);
        return ResponseEntity.status(200).body("Collaboration request sent.");
    }

    @PostMapping("/approve-collaboration/{serviceId}/{designerId}")
    public ResponseEntity approveCollaboration(@PathVariable Integer serviceId, @PathVariable Integer designerId) {
        Boolean success = designerService.approveCollaboration(serviceId, designerId);
        if (success) {
            return ResponseEntity.status(200).body("Collaboration approved.");
        } else {
            return ResponseEntity.status(400).body("Failed to approve collaboration.");
        }
    }


    @PutMapping("/reject-collaboration/{serviceId}/{designerId}")
    public ResponseEntity rejectCollaboration(@PathVariable Integer serviceId, @PathVariable Integer designerId){
        Boolean failed = designerService.rejectCollaboration(serviceId, designerId);
        if (failed) {
            return ResponseEntity.status(200).body("Collaboration rejected.");
        } else {
            return ResponseEntity.status(400).body("Failed to reject collaboration.");
        }
    }


    // Get feedback for a service
    @GetMapping("/feedback/{serviceId}")
    public ResponseEntity getFeedback(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(servicesService.getFeedbackForService(serviceId));
    }

    // Get ratings for a service
    @GetMapping("/ratings/{serviceId}")
    public ResponseEntity getRatings(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(servicesService.getRatingsForService(serviceId));
    }

    @PostMapping("/rating")
    public ResponseEntity<String> rateService(@RequestBody Rate rate,Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean success = servicesService.addRatingAndFeedback(rate);
        if (success) {
            return ResponseEntity.status(200).body("Rating and feedback added successfully");
        }
        return ResponseEntity.status(400).body("Failed to add rating and feedback");
    }

}
