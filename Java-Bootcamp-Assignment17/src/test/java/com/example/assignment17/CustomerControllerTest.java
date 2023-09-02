package com.example.assignment17;


import com.example.assignment17.Api.ApiResponse;
import com.example.assignment17.Controller.CustomerController;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.User;
import com.example.assignment17.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) //to use some annotations in this class
@WebMvcTest(value = CustomerController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class}) //first argument to select controller, second argument to disable security config
public class CustomerControllerTest {

    @MockBean // to encapsulate controller class and give it virtual data, commonly used with controller test
    CustomerService customerService;


    @Autowired
    MockMvc mockMvc; //?

    Customer customer1, customer2;
    User user1, user2;

    ApiResponse apiResponse;
    List<Customer> customers, customerList;

    @BeforeEach
    void setUp(){
        user1=new User(null,"bashaer2020","12345678","CUSTOMER",null, null);
        user2=new User(null,"Ahmed2023","12345678","CUSTOMER",null, null);
        customer1=new Customer(null,"bashaerh","bashaersa@Hotmail.com",null,user1,null);
        customer2=new Customer(null,"Ahmedho","Ahmed2023@Hotmail.com",null,user2,null);

        customers= Arrays.asList(customer1);
        customerList= Arrays.asList(customer2);
    }

    @Test
    public void getCustomers() throws Exception{
        Mockito.when(customerService.getAll()).thenReturn(customers); //Service method that called in controller method
        mockMvc.perform(get("/api/v1/customer/get")) //Request path
                .andExpect(status().isOk()) //Response status
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))//check of list size
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("bashaerh"));//check of value in list
    }

    @Test
    public void addCustomer() throws Exception{
        mockMvc.perform(post("/api/v1/customer/add",user1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customer1))) //customer1 must be equal to data in virtual db
                .andExpect(status().isOk());
    }

    @Test
    public void updateCustomer() throws Exception {
        mockMvc.perform(put("/api/v1/customer/update",user1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customer1))) //customer1 must be equal to data in virtual db
                .andExpect(status().isOk());
    }
    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/delete",user1))
                .andExpect(status().isOk());
    }
    @Test
    public void getCustomerByUsername() throws Exception {
        Mockito.when(customerService.getByUsername(user1.getUsername())).thenReturn(customer1); //Service method that called in controller method

        mockMvc.perform(get("/api/v1/customer/getByUsername/{username}",user1.getUsername()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("bashaerh"));//check of value in list

    }

    }
