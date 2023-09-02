package com.example.assignment17;


import com.example.assignment17.Model.*;
import com.example.assignment17.Repository.*;
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

public class OfferRepositoryTest {

    @Autowired //for dependency injection
    AuthRepository authRepository;
    @Autowired //for dependency injection
    CustomerRepository customerRepository;
    @Autowired //for dependency injection
    ProjectRepository projectRepository;
    @Autowired //for dependency injection
    ExpertRepository expertRepository;
    @Autowired //for dependency injection
    OfferRepository offerRepository;

    User user;
    User user1;

    Customer customer;

    Project project;

    Expert expert;

    Offer offer;
    Offer offer1;
    List<Offer> offers;

    @BeforeEach
    void setUp(){
        user=new User(null,"bashaer2020","12345678","CUSTOMER",null, null);
        user1=new User(null,"ahmed2020","12345678","EXPERT",null, null);
        customer=new Customer(null,"bashaerh","bashaersa@Hotmail.com",null,user,null);
        expert=new Expert(null,"ahmedhotha","ahmedho@Hotmail.com","web","I am intersted web devlopment",null,user1,null);
        project=new Project(null,"ordering website","website for ordering food","opened",2000,"web",null,customer,null);
        offer=new Offer(null,"I have three years experience in back-end web developmen",1500,null,expert,project);
        offer1=new Offer(null,"I can to implement your project",1500,null,expert,project);
    }

    @Test
    void findOfferByProject(){

        authRepository.save(user);
        customerRepository.save(customer);
        expertRepository.save(expert);
        projectRepository.save(project);
        offerRepository.save(offer);
        offerRepository.save(offer1);

        offers=offerRepository.findOfferByProject(project);
        Assertions.assertThat(offers.get(0).getProject().getId()).isEqualTo(project.getId());
    }

    @Test
    void findOfferByExpert(){
        authRepository.save(user);
        customerRepository.save(customer);
        expertRepository.save(expert);
        projectRepository.save(project);
        offerRepository.save(offer);
        offerRepository.save(offer1);

        offers=offerRepository.findOfferByExpert(expert);
        Assertions.assertThat(offers.get(0).getExpert().getId()).isEqualTo(offer.getExpert().getId());

    }




}
