package com.example.assignment17.Service;


import com.example.assignment17.Api.ApiException;
import com.example.assignment17.DTO.ExpertDTO;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.Expert;
import com.example.assignment17.Model.Project;
import com.example.assignment17.Model.User;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.CustomerRepository;
import com.example.assignment17.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;

    public List<Project> get(){
        return projectRepository.findAll();
    }


    public void add(Project project, User user){
        User user1=authRepository.findUserById(user.getId());
        if(user1==null)
            throw new ApiException("User id not found");
        Customer customer=customerRepository.findCustomerByUser(user1);
        if(customer==null)
            throw new ApiException("Customer not found");
        project.setCustomer(customer);
        project.setCreatedAt(LocalDate.now());
        projectRepository.save(project);
    }

    public void update(Project project, User user, Integer id){
        Customer customer=customerRepository.findCustomerByUser(user);
        if(customer==null)
            throw new ApiException("Customer details not found");

        Project oldProject=projectRepository.findProjectByCustomerAndId(customer,id);
        if(oldProject==null)
            throw new ApiException("Project not found");

        oldProject.setTitle(project.getTitle());
        oldProject.setDescription(project.getDescription());
        oldProject.setCategory(project.getCategory());
        oldProject.setMaxPrice(project.getMaxPrice());
        oldProject.setStatus(project.getStatus());
        projectRepository.save(oldProject);
    }

    public void delete(User user,Integer id){
        Customer customer=customerRepository.findCustomerByUser(user);
        if(customer==null)
            throw new ApiException("Customer details not found");

        Project project=projectRepository.findProjectByCustomerAndId(customer,id);
        if(project==null)
            throw new ApiException("Project not found");

        projectRepository.delete(project);
    }

    public Project getById(Integer id){
        Project project=projectRepository.findProjectById(id);
        if(project==null)
            throw new ApiException("Project id not found");

        return project;
    }

    public List<Project> getByCategory(String category){
        List<Project> projects=projectRepository.findProjectByCategory(category);
        if(projects==null)
            throw new ApiException("Projects not found");

        return projects;
    }

    public List<Project> getByStatus(String status){
        List<Project> projects=projectRepository.findProjectByStatus(status);
        if(projects==null)
            throw new ApiException("Projects not found");

        return projects;
    }

    public List<Project> getByPriceGreaterThan(Integer price){
        List<Project> projects=projectRepository.findProjectByMaxPriceGreaterThanEqual(price);
        if(projects==null)
            throw new ApiException("Projects not found");

        return projects;
    }
    public List<Project> getByCreatedAtAfter(LocalDate date){
        List<Project> projects=projectRepository.findProjectByCreatedAtAfter(date);
        if(projects==null)
            throw new ApiException("Projects not found");

        return projects;
    }

    public void changeStatus(User user,Integer id){

        Customer customer=customerRepository.findCustomerByUser(user);
        if(customer==null)
            throw new ApiException("Customer details not found");

        Project project=projectRepository.findProjectByCustomerAndId(customer,id);
        if(project==null)
            throw new ApiException("Project not found");

        if (project.getStatus().equals("opened"))
            project.setStatus("finished");
        else
            project.setStatus("opened");

        projectRepository.save(project);

    }


}
