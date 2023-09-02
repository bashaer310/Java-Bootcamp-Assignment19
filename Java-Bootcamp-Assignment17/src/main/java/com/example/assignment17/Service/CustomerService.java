package com.example.assignment17.Service;


import com.example.assignment17.Api.ApiException;
import com.example.assignment17.DTO.CustomerDTO;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.User;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public void add(CustomerDTO customerDTO, User user){
        User user1=authRepository.findUserById(user.getId());
        if(user1==null)
            throw new ApiException("User id not found");
        /*if(!(user1.getRole().equals("CUSTOMER")))
            throw new ApiException("User role not equals CUSTOMER");*/

        Customer customer=new Customer(null,customerDTO.getName(),customerDTO.getEmail(),LocalDate.now(),user1,null);
        customerRepository.save(customer);
    }

    public void update(CustomerDTO customer, User user){
        Customer oldCustomer=customerRepository.findCustomerByUser(user);
        if(oldCustomer==null)
            throw new ApiException("Customer not found");
        oldCustomer.setName(customer.getName());
        oldCustomer.setEmail(customer.getEmail());
        customerRepository.save(oldCustomer);
    }

    public void delete(User user){
        Customer customer=customerRepository.findCustomerByUser(user);
        if(customer==null)
            throw new ApiException("Customer not found");
        user.setCustomer(null);
        authRepository.save(user);
        customerRepository.delete(customer);
    }

    public Customer getByUsername(String username){
        User user=authRepository.findUserByUsername(username);
        Customer customer = customerRepository.findCustomerByUser(user);
        if(customer==null || user==null)
            throw new ApiException("Customer username or customer details not found");

        return customer;
    }
}
