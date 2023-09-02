package com.example.assignment17.Repository;


import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findProjectById(Integer id);
    //Project findProjectByCustomer(Customer customer);
    Project findProjectByCustomerAndId(Customer customer,Integer id);
    List<Project> findProjectByCategory(String category);
    List<Project> findProjectByStatus(String status);
    List<Project> findProjectByMaxPriceGreaterThanEqual(Integer price);
    List<Project> findProjectByCreatedAtAfter(LocalDate date);

}
