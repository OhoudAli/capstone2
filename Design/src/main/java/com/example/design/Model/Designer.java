package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String username;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty
    @Column(columnDefinition = "varchar(150) not null")
    private String aboutMe;


}
