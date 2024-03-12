package com.example.exercisevalidation.Controller;

import com.example.exercisevalidation.Api.ApiResponse;
import com.example.exercisevalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/projeect")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getPro() {
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Project projected, Errors errors) {
        if (errors.hasErrors()) {
            String mesge = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mesge);
        }
        projects.add(projected);
        return ResponseEntity.status(200).body(new ApiResponse("added"));
    }
    @PutMapping("/update/{index}")
    public  ResponseEntity upProject(@PathVariable int index, @RequestBody @Valid Project projected, Errors errors) {
        if (errors.hasErrors()) {
            String mesge = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(mesge);
        }
        projects.set(index, projected);
        return ResponseEntity.status(200).body(new ApiResponse("updated"));
    }
    @DeleteMapping("/delete/{index}")
    public  ResponseEntity delete(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("delete"));
    }
    @PostMapping("/change-status/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable String id, @RequestBody String status) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                project.setStatus(status);
                return ResponseEntity.status(200).body(new ApiResponse("Status"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("to fail"));
    }
    }
    @GetMapping("/search/{title}")
    public ArrayList<Project> search(@PathVariable String title){
        ArrayList<Project>proj =new ArrayList<>();
        for (Project project:projects){
            if (project.getTitle().equalsIgnoreCase(title)){
                proj.add(project);
            }
        }
        return proj;
    }
    @GetMapping("proj/{companyName}")
    public ArrayList<Project> company(@PathVariable String companyName){
        ArrayList<Project>proje=new ArrayList<>();
        for (Project project:projects){
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                proje.add(project);
            }
        }
        return proje;
    }
}
