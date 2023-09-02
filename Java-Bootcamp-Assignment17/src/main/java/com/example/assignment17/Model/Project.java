package com.example.assignment17.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Title must not be empty")
    @Size(min = 10, message = "Title length must be more than 10")
    @Column(columnDefinition = "varchar(50) not null CHECK(LENGTH(title)>=10)")
    private String title;

    @NotEmpty(message = "Description must not be empty")
    @Size(min = 20, message = "Description length must be more than 20")
    @Column(columnDefinition = "varchar(150) not null CHECK(LENGTH(description)>=20)")
    private String description;

    @NotEmpty(message = "Status must not be empty")
    @Pattern(regexp = "opened|finished")
    @Column(columnDefinition = "varchar(8) not null CHECK(status='opened' OR status='finished')")
    private String status;

    @NotNull(message = "Max Price must not be empty")
    @Positive(message ="Max Price must be a positive number" )
    //CHECK(maxPrice>'0')**
    @Column(columnDefinition = "int UNSIGNED not null")
    private Integer maxPrice;


    @NotEmpty(message = "Category must not be empty")
    @Pattern(regexp = "web|apps|gaming",message = "Category must be one of these: apps development, game programming, web development")
    @Column(columnDefinition = "varchar(25) not null CHECK(category REGEXP 'web|apps|gaming')")
    private String category;

    @Column(columnDefinition = "DATE default CURRENT_DATE()")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "user_id")
    @JsonIgnore
    private Customer customer;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Offer> offers;

}
