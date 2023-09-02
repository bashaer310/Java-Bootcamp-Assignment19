package com.example.assignment17.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Offer must not be empty")
    @Size(min = 10, message = "Offer length must be more than 10")
    @Column(columnDefinition = "varchar(150) not null CHECK(LENGTH(offer)>=10)")
    private String offer;

    @NotNull(message = "Min Price must not be empty")
    @PositiveOrZero(message ="Min Price must be a positive number" )
    //CHECK(min_price>=0)**
    @Column(columnDefinition = "int UNSIGNED not null")
    private Integer minPrice;

    @Column(columnDefinition = "DATE default CURRENT_DATE()")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "expert_id",referencedColumnName = "user_id")
    @JsonIgnore
    private Expert expert;

    @ManyToOne
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    @JsonIgnore
    private Project project;


}
