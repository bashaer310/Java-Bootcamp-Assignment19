package com.example.assignment17.Controller;

import com.example.assignment17.Api.ApiResponse;
import com.example.assignment17.DTO.CustomerDTO;
import com.example.assignment17.Model.Customer;
import com.example.assignment17.Model.User;
import com.example.assignment17.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getCustomers(){
        return ResponseEntity.status(200).body(customerService.getAll());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomer(@AuthenticationPrincipal User user,@RequestBody @Valid CustomerDTO customer){
        customerService.add(customer,user);
        return ResponseEntity.status(200).body(new ApiResponse("Customer details added"));
    }


    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody @Valid CustomerDTO customer,@AuthenticationPrincipal User user) {
        customerService.update(customer,user);
        return ResponseEntity.status(200).body(new ApiResponse("Customer details updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
        customerService.delete(user);
        return ResponseEntity.status(200).body(new ApiResponse("Customer details deleted"));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity getCustomerByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(customerService.getByUsername(username));
    }

}
