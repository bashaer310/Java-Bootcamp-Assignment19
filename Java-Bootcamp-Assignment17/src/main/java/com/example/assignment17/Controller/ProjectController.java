package com.example.assignment17.Controller;


import com.example.assignment17.Api.ApiResponse;
import com.example.assignment17.Model.Project;
import com.example.assignment17.Model.User;
import com.example.assignment17.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;


    @GetMapping("/get")
    public ResponseEntity getProjects(){
        return  ResponseEntity.status(200).body(projectService.get());
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@AuthenticationPrincipal User user, @RequestBody @Valid Project project){
        projectService.add(project,user);
        return ResponseEntity.status(200).body(new ApiResponse("Project added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@AuthenticationPrincipal User user, @RequestBody @Valid Project project, @PathVariable Integer id) {
        projectService.update(project,user,id);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@AuthenticationPrincipal User user,@PathVariable Integer id) {
        projectService.delete(user,id);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted"));
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity getProjectById(@PathVariable Integer id){
        return  ResponseEntity.status(200).body(projectService.getById(id));
    }
    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getProjectsByCategory(@PathVariable String category){
        return  ResponseEntity.status(200).body(projectService.getByCategory(category));
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity getProjectsByStatus(@PathVariable String status){
        return  ResponseEntity.status(200).body(projectService.getByStatus(status));
    }
    @GetMapping("/getByPrice/{price}")
    public ResponseEntity getProjectsByPrice(@PathVariable Integer price){
        return  ResponseEntity.status(200).body(projectService.getByPriceGreaterThan(price));
    }
    @GetMapping("/getByDate/{date}")
    public ResponseEntity getProjectsByDate(@PathVariable LocalDate date){
        return  ResponseEntity.status(200).body(projectService.getByCreatedAtAfter(date));
    }

    @PutMapping("/changeStatus/{id}")
    public ResponseEntity changeStatus(@AuthenticationPrincipal User user,@PathVariable Integer id){
        projectService.changeStatus(user,id);
        return  ResponseEntity.status(200).body(new ApiResponse("Status changed"));

    }


}
