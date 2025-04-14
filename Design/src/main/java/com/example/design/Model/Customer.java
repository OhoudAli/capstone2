package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String username;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @Column(columnDefinition = "int not null")
    private Integer orderId;

    @NotNull
    @Positive
    @Column(columnDefinition = "double not null")
    private Double balance;

    @Column(columnDefinition = "int not null")
    private Integer designId;


}
