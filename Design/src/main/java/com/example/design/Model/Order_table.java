package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class Order_table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int")
    private Integer customer_id;

    @Column(columnDefinition = "int ")
    private Integer design_id;

    @Column(columnDefinition = "varchar(20) ")
    private String name;

    @Column(columnDefinition = "int ")
    private Integer price;

    @Column(columnDefinition = "int ")
    private Integer quantity;

    @Column(columnDefinition = "date ")
    private LocalDate orderDate =LocalDate.now();

    @Column(columnDefinition = "varchar(20)")
    private String orderStatus="pending";

}
