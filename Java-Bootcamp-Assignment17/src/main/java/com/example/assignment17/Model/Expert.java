package com.example.assignment17.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expert {

    @Id
    private Integer id;


    @NotEmpty(message = "Name must be not empty")
    @Size(min = 3,message = "Name length must be more than 3")
    @Column(columnDefinition = "varchar(12) not null CHECK(LENGTH(name)>=3)")
    private String name;


    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be a valid format")
    //@UniqueElements(message = "Email must be unique")
    @Column(columnDefinition = "varchar(30) unique not null CHECK(email REGEXP '([a-zA-Z0-9]{6,})(@)([a-zA-Z]{6,})(\\.)([a-zA-Z]{2,})')")
    private String email;


    @NotEmpty(message = "Major must not be empty")
    @Pattern(regexp = "web|apps|gaming",message = "Major must be one of these: apps development, game programming, web development")
    @Column(columnDefinition = "varchar(25) not null CHECK(major REGEXP 'web|apps|gaming')")
    private String major;

    @NotEmpty(message = "Profile must not be empty")
    @Size(min = 10, message = "Profile length must be more than 10")
    @Column(columnDefinition = "varchar(150) not null CHECK(LENGTH(profile)>=10)")
    private String profile;

    @Column(columnDefinition = "DATE default CURRENT_DATE()")
    private LocalDate createdAt;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expert")
    private Set<Offer> offers;

}
