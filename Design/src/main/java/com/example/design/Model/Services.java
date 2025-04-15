package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) ")
    private String type;

    @NotNull(message = "Designer ID ")
    @Column(columnDefinition = "int")
    private Integer designerId;

    @Column(columnDefinition = "int")
    private Integer designerId1;

    @Column(columnDefinition = "int ")
    private Integer customerId;

    @NotEmpty(message = "Details cannot be empty")
    @Column(columnDefinition = "varchar(200) ")
    private String details;

    @Column(columnDefinition = "varchar(10)")
    private String requestStatus = "pending";

    @Column(columnDefinition = "date not null")
    private LocalDate date = LocalDate.now();
}
