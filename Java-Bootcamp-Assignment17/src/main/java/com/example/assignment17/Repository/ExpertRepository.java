package com.example.assignment17.Repository;

import com.example.assignment17.Model.Expert;
import com.example.assignment17.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Integer> {
    Expert findExpertById(Integer id);
    Expert findExpertByUser(User user);
    List<Expert> findExpertByMajor(String major);
   //Expert findExpertModelByEmailAndPassword(String email,String password);

   // Expert findExpertModelByUsername(String username);
}
