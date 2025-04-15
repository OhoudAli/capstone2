package com.example.design.Service;


import com.example.design.Model.Designer;
import com.example.design.Model.Services;
import com.example.design.Repository.DesignerRepository;
import com.example.design.Repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;
    private final ServicesRepository servicesRepository;

    public List<Designer> getAllDesigner(){
        return designerRepository.findAll();
    }

    public void addDesigner(Designer designer){
        designerRepository.save(designer);
    }

    public Boolean updateDesigner(Integer id, Designer designer){
        Designer oldDesigner = designerRepository.findDesignerById(id);

        if(oldDesigner == null){
            return false;
        }

        oldDesigner.setName(designer.getName());
        oldDesigner.setEmail(designer.getEmail());
        oldDesigner.setAboutMe(designer.getAboutMe());
        oldDesigner.setUsername(designer.getUsername());
        oldDesigner.setPassword(designer.getPassword());

        designerRepository.save(oldDesigner);
        return true;
    }

    public Boolean deleteDesigner(Integer id){
        Designer designer = designerRepository.findDesignerById(id);
        if (designer == null){
            return false;
        }
        designerRepository.delete(designer);
        return true;
    }

    public Designer getDesignerById(Integer id) {
        return designerRepository.findDesignerById(id);
    }

    public Boolean createCollaboration(Integer designerId, Integer designerId1, String type) {
        Designer designer = designerRepository.findDesignerById(designerId);
        Designer designer1 = designerRepository.findDesignerById(designerId1);

        if(designer == null || designer1 == null){
            return false;
        }
        Services services = new Services();
        services.setDesignerId(designerId);
        services.setDesignerId1(designerId1);
        String details = "Collaboration request between designer " + designer.getName() + " and designer " + designer1.getName();
        services.setDetails(details);
            servicesRepository.save(services);
        return true;
    }

    public Boolean approveCollaboration(Integer serviceId, Integer designerId) {
        Services collaboration = servicesRepository.findServicesById(serviceId);
        if (collaboration == null || !collaboration.getDesignerId().equals(designerId)) {
            return false;
        }
        if(collaboration.getRequestStatus().equals("Rejected")){
            return false;
        }
        collaboration.setRequestStatus("Approved");
        servicesRepository.save(collaboration);
        return true;
    }

    public Boolean rejectCollaboration(Integer serviceId, Integer designerId){
        Services collab = servicesRepository.findServicesById(serviceId);
        if (collab == null || !collab.getDesignerId().equals(designerId)) {
            return false;
        }
        if(collab.getRequestStatus().equals("Approved")){
            return false;
        }
        collab.setRequestStatus("Rejected");
        servicesRepository.save(collab);
        return true;
    }




}
