package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @Column(columnDefinition = "varchar(20) ")
    private String name;

    @Column(columnDefinition = "varchar(20) ")
    private String username;

    @Column(columnDefinition = "varchar(20) ")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password must contain letters ,digits and special character")
    private String password;

    @Column(columnDefinition = "varchar(20)  unique")
    private String email;


    @Positive
    @Column(columnDefinition = "double ")
    private Double balance;




}
