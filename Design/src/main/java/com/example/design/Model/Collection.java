package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Column(columnDefinition = "int not null")
    private Integer designId;

    @Column(columnDefinition = "double not null")
    private Double price;

    @Column(columnDefinition = "date not null")
    private LocalDate releaseDate= LocalDate.now();
}
