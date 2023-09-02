package com.example.assignment17.Repository;

import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    //Customer findCustomerById(Integer id);
    Customer findCustomerByUser(User user);
    //Customer findCustomerModelByUsername(String username);
   //Customer findCustomerModelByEmailAndPassword(String email,String password);
}
