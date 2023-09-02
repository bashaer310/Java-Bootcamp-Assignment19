package com.example.assignment17;

import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.Project;
import com.example.assignment17.Model.User;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.CustomerRepository;
import com.example.assignment17.Repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class) //to call annotations in this class
@DataJpaTest //to tell JPA this class handles with repo
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //to ignore security
public class ProjectRepositoryTest {

    @Autowired //for dependency injection
    CustomerRepository customerRepository;
    @Autowired //for dependency injection
    AuthRepository authRepository;
    @Autowired //for dependency injection
    ProjectRepository projectRepository;

    User user;

    Customer customer;

    Project project;
    Project project1;
    List<Project> projects;

    @BeforeEach
    void setUp(){
        user=new User(null,"bashaer2020","12345678","CUSTOMER",null, null);
        customer=new Customer(null,"bashaerh","bashaersa@Hotmail.com",null,user,null);
        project=new Project(null,"ordering website","website for ordering food","opened",2000,"web",null,customer,null);
        project1=new Project(null,"reservation website","website for reservation hotel food","opened",2000,"web",null,customer,null);

    }

    @Test
    void findProjectByCategory(){
        authRepository.save(user);
        customerRepository.save(customer);
        projectRepository.save(project);
        projectRepository.save(project1);


        projects=projectRepository.findProjectByCategory("web");
        Assertions.assertThat(projects.get(0).getCategory()).isEqualTo(project.getCategory());
    }

    @Test
    void findProjectByStatus(){
        authRepository.save(user);
        customerRepository.save(customer);
        projectRepository.save(project);
        projectRepository.save(project1);


        projects=projectRepository.findProjectByStatus("opened");
        Assertions.assertThat(projects.get(0).getStatus()).isEqualTo(project.getStatus());
    }
    @Test
    void findProjectByMaxPriceGreaterThanEqual(){
        authRepository.save(user);
        customerRepository.save(customer);
        projectRepository.save(project);
        projectRepository.save(project1);


        projects=projectRepository.findProjectByMaxPriceGreaterThanEqual(1999);
        Assertions.assertThat(projects.get(0).getMaxPrice()).isGreaterThan(1999);
    }




}
