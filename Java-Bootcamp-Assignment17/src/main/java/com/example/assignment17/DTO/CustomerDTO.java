package com.example.assignment17.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "Name must be not empty")
    @Size(min = 3,message = "Name length must be more than 3")
    @Column(columnDefinition = "varchar(15) not null CHECK(LENGTH(name)>=3)")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid format")
    @Column(columnDefinition = "varchar(30) unique not null CHECK(email REGEXP '([a-zA-Z0-9]{6,})(@)([a-zA-Z]{6,})(\\.)([a-zA-Z]{2,})')")
    private String email;
}
