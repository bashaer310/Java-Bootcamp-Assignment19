package com.example.assignment17.Service;


import com.example.assignment17.Api.ApiException;
import com.example.assignment17.DTO.CustomerDTO;
import com.example.assignment17.DTO.ExpertDTO;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.Expert;
import com.example.assignment17.Model.User;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.CustomerRepository;
import com.example.assignment17.Repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertRepository expertRepository;
    private final AuthRepository authRepository;

    public List<Expert> getAll(){
        return expertRepository.findAll();
    }
    public void add(ExpertDTO expertDTO, User user){
        User user1=authRepository.findUserById(user.getId());
        if(user1==null)
            throw new ApiException("User id not found");

        if(!(user1.getRole().equals("EXPERT")))
            throw new ApiException("User role not equals EXPERT");

        Expert expert=new Expert(null, expertDTO.getName(), expertDTO.getEmail(), expertDTO.getMajor(), expertDTO.getProfile(),LocalDate.now(),user1,null);
        expertRepository.save(expert);
    }

    public void update(ExpertDTO expertDTO, User user){
        Expert oldExpert=expertRepository.findExpertByUser(user);
        if(oldExpert==null)
            throw new ApiException("Expert not found");

        oldExpert.setName(expertDTO.getName());
        oldExpert.setEmail(expertDTO.getEmail());
        oldExpert.setMajor(expertDTO.getMajor());
        oldExpert.setProfile(expertDTO.getProfile());
        expertRepository.save(oldExpert);
    }

    public void delete(User user){
        Expert expert=expertRepository.findExpertByUser(user);
        if(expert==null)
            throw new ApiException("Expert not found");

        user.setExpert(null);
        authRepository.save(user);
        expertRepository.delete(expert);
    }

    public Expert getByUsername(String username){
        User user=authRepository.findUserByUsername(username);
        Expert expert = expertRepository.findExpertByUser(user);
        if(expert==null || user==null)
            throw new ApiException("Expert username or expert details not found");
        return expert;
    }

    public List<Expert> getByMajor(String major){
        List<Expert> experts=expertRepository.findExpertByMajor(major);
        if(experts==null)
            throw new ApiException("Experts not found");

        return experts;
    }


}
