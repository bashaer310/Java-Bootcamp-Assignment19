package com.example.assignment17;

import com.example.assignment17.DTO.ExpertDTO;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.Expert;
import com.example.assignment17.Model.Project;
import com.example.assignment17.Model.User;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.CustomerRepository;
import com.example.assignment17.Repository.ExpertRepository;
import com.example.assignment17.Repository.ProjectRepository;
import com.example.assignment17.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //to be able handel with mock method

public class ProjectServiceTest {


    @InjectMocks //for dependency injection
    ProjectService projectService;


    @Mock //to give specific value for any repo method (vir
    ProjectRepository projectRepository;
    @Mock //to give specific value for any repo method (virtual DB)
    CustomerRepository customerRepository;
    @Mock //to give specific value for any repo method (virtual DB)
    AuthRepository authRepository;

    User user;

    Customer customer;

    Project project;


    @BeforeEach
    void setUp(){
        user=new User(null,"bashaer2020","12345678","CUSTOMER",null, null);
        customer=new Customer(null,"bashaerh","bashaersa@Hotmail.com",null,user,null);
        project=new Project(null,"ordering website","website for ordering food","opened",2000,"web",null,customer,null);

    }

    @Test
    public void add(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        when(customerRepository.findCustomerByUser(user)).thenReturn(customer);
        projectService.add(project,user);
        Mockito.verify(projectRepository,times(1)).save(project); //how many times call findAll method (in expert service)
        Mockito.verify(authRepository,times(1)).findUserById(user.getId()); //how many times call findAll method (in expert service)
        Mockito.verify(customerRepository,times(1)).findCustomerByUser(user); //how many times call findAll method (in expert service)
    }

    @Test
    public void delete(){
        when(customerRepository.findCustomerByUser(user)).thenReturn(customer);
        when(projectRepository.findProjectByCustomerAndId(customer,project.getId())).thenReturn(project);

        projectService.delete(user,project.getId());
        Mockito.verify(customerRepository,times(1)).findCustomerByUser(user); //how many times call findAll method (in expert service)
        Mockito.verify(projectRepository,times(1)).findProjectByCustomerAndId(customer,project.getId()); //how many times call findAll method (in expert service)
        Mockito.verify(projectRepository,times(1)).delete(project); //how many times call findAll method (in expert service)
    }

}
