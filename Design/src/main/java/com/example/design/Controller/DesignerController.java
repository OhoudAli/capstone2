package com.example.design.Controller;


import com.example.design.Model.Designer;
import com.example.design.Model.Services;
import com.example.design.Service.DesignerService;
import com.example.design.Service.ServicesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/designer")
public class DesignerController {

    private final DesignerService designerService;
    private final ServicesService servicesService;



    @GetMapping("/get")
    public ResponseEntity getAllDesigner(){
        return ResponseEntity.status(200).body(designerService.getAllDesigner());
    }

    @PostMapping("/add")
    public ResponseEntity addDesigner(@RequestBody @Valid Designer designer, Errors errors){

        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        designerService.addDesigner(designer);
        return ResponseEntity.status(200).body("designer added successfully");

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateDesigner(@PathVariable Integer id, @RequestBody @Valid Designer designer,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        designerService.updateDesigner(id, designer);
           return ResponseEntity.status(200).body("Designer updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDesigner(@PathVariable Integer id) {
        boolean isDeleted = designerService.deleteDesigner(id);
        if(isDeleted){
            return ResponseEntity.ok("Designer deleted successfully");
        }
        return ResponseEntity.status(400).body("Designer not found");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getDesignerById(@PathVariable Integer id) {
        Designer designer = designerService.getDesignerById(id);
        if(designer == null){
          return   ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(designer);
    }



}
