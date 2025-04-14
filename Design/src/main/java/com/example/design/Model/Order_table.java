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
    @Column(columnDefinition = "int")
    private Integer collection_id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer price;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @Column(columnDefinition = "date ")
    private LocalDate orderDate =LocalDate.now();

    @Column(columnDefinition = "varchar(20)")
    @Pattern(regexp = "Collection|Design")
    private String itemKind;


    @Column(columnDefinition = "varchar(20)")
    private String orderStatus="pending";

}
