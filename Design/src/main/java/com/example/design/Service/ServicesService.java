package com.example.design.Service;


import com.example.design.Model.Customer;
import com.example.design.Model.Designer;
import com.example.design.Model.Rate;
import com.example.design.Model.Services;
import com.example.design.Repository.CustomerRepository;
import com.example.design.Repository.DesignerRepository;
import com.example.design.Repository.RateRepository;
import com.example.design.Repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ServicesService {



    private final ServicesRepository servicesRepository;
    private final DesignerRepository designerRepository;
    private final RateRepository rateRepository;
    private final CustomerRepository customerRepository;

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getServiceById(Integer id) {
        return servicesRepository.findServicesById(id);
    }

    public void addService(Services services) {
        servicesRepository.save(services);
    }

    public Boolean updateService(Integer id, Services services){
        Services oldService = servicesRepository.findServicesById(id);
        if(oldService == null){
            return false;
        }
        oldService.setType(services.getType());
        oldService.setDetails(services.getDetails());
        oldService.setDesignerId(services.getDesignerId());

        servicesRepository.save(oldService);
        return true;
    }

    public Boolean deleteService(Integer id) {
        Services service = servicesRepository.findServicesById(id);
        if (service == null) {
            return false;
        }
        servicesRepository.delete(service);
        return true;
    }


    public void requestShoppingAssistance(Services services) {
        services.setType("help_request");
        servicesRepository.save(services);
    }


    public Boolean approveAssistanceRequest(Integer serviceId, Integer designerId) {
        Services service = servicesRepository.findServicesById(serviceId);
        Designer designer = designerRepository.findDesignerById(designerId);
        if (service == null ) {
            return false;
        }
        if( designer == null){
            return false;
        }
        if(service.getRequestStatus().equals("Rejected")){
            return false;
        }
        service.setRequestStatus("Approved");
        servicesRepository.save(service);
        return true;
    }

    public List<Services> getRequestsForDesigner(Integer designerId) {
        return servicesRepository.findByDesignerId(designerId);
    }
    public List<Services> getServicesForDesigner(Integer designerId) {
        return servicesRepository.findByDesignerId(designerId);
    }


    public String rejectHelp(Integer serviceId , Integer designerId) {
        Services service = servicesRepository.findServicesById(serviceId);
        Designer designer= designerRepository.findDesignerById(designerId);
        if (service == null || designer == null) {
            return "rejected failed";
        }
        if(service.getRequestStatus().equals("Approved")){
            return "You can not reject approved request";
        }
        service.setRequestStatus("Rejected");
        servicesRepository.save(service);
        return "Request rejected";
    }

    public String getServiceStatus(Integer serviceId) {
        Services service = servicesRepository.findServicesById(serviceId);
        if(service== null){
            return "Service not found";
        }
        return service.getRequestStatus();
    }


    // Add rating and feedback for a service
    public Boolean addRatingAndFeedback(Rate rate) {
        Services service = servicesRepository.findServicesById(rate.getServiceId());
        Customer customer = customerRepository.findCustomerById(rate.getCustomerId());

        if (service != null && customer != null) {
            rateRepository.save(rate);
            return true;
        }
        return false;
    }

    // Get ratings for a specific service
    public List<Rate> getRatingsForService(Integer serviceId) {
        return rateRepository.findByServiceId(serviceId);
    }

    // Get all feedback for a service
    public String getFeedbackForService(Integer serviceId) {
        List<Rate> rates = rateRepository.findByServiceId(serviceId);
        String feedback = "";
        for (Rate rate : rates) {
            feedback += rate.getFeedback() + "\n";
        }

        return feedback;
    }


}
