package com.example.assignment17;

import com.example.assignment17.DTO.ExpertDTO;
import com.example.assignment17.Model.*;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.ExpertRepository;
import com.example.assignment17.Service.ExpertService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //to be able handel with mock method

public class ExpertServiceTest {

    @InjectMocks //for dependency injection
    ExpertService expertService;


    @Mock //to give specific value for any repo method (virtual DB)
    ExpertRepository expertRepository;
    @Mock //to give specific value for any repo method (virtual DB)
    AuthRepository authRepository;


    User user;
    User user1;

    Expert expert;
    ExpertDTO expertDto;

    Expert expert1;

    List<Expert> experts;

    @BeforeEach
    void setUp(){
        user=new User(null,"bashaer2020","12345678","CUSTOMER",null, null);
        user1=new User(null,"ahmed2020","12345678","EXPERT",null, null);
        expertDto=new ExpertDTO("bashaerhotha","bashaerhotha@Hotmail.com","web","I am intersted web devlopment");
        expert=new Expert(null,expertDto.getName(),expertDto.getEmail(),expertDto.getMajor(),expertDto.getProfile(),null,user,null);
        expert1=new Expert(null,"ahmedhotha","ahmedho@Hotmail.com","web","I am intersted web devlopment",null,user1,null);

        experts=new ArrayList<>(); //because auth&expert repo is mock, they can't store or retrieve data from them.  will be store and retrieve data from ArrayList
        experts.add(expert);
        experts.add(expert1);

    }

    @Test
    public void getAll(){
        when(expertRepository.findAll()).thenReturn(experts); //what returned values from findAll method (in expert service)
        List<Expert> expertList=expertService.getAll();
        Assertions.assertEquals(experts.size(),expertList.size());
        verify(expertRepository,times(1)).findAll(); //how many times call findAll method (in expert service)
    }
    /*
    @Test
    public void add(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        expertService.add(expertDto,user);
        verify(expertRepository,times(1)).save(expert);
        verify(authRepository,times(1)).findUserById(user.getId()); //how many times call findAll method (in expert service)
    }
    @Test
    public void delete(){
        when(authRepository.findUserById(user.getId())).thenReturn(user);
        user.setExpert(null);
        authRepository.save(user);
        expertService.delete(user);
        verify(expertRepository,times(1)).delete(expert);
        verify(authRepository,times(1)).findUserById(user.getId()); //how many times call findAll method (in expert service)

    }
    */
    @Test
    public void getByUsername(){
        when(authRepository.findUserByUsername(user.getUsername())).thenReturn(user); //what returned values from findAll method (in expert service)
        when(expertRepository.findExpertByUser(user)).thenReturn(expert);

        Expert expert2=expertService.getByUsername(user.getUsername());
        Assertions.assertEquals(expert.getId(),expert2.getId());

        verify(authRepository,times(1)).findUserByUsername(user.getUsername());
        verify(expertRepository,times(1)).findExpertByUser(user);

    }

    @Test
    public void getByMajor(){
        when(expertRepository.findExpertByMajor(expert.getMajor())).thenReturn(experts);

        List<Expert> expertList=expertService.getByMajor(expert.getMajor());
        Assertions.assertEquals(experts,expertList);

        verify(expertRepository,times(1)).findExpertByMajor(expert.getMajor());
    }


}
