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
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Column(columnDefinition = "int not null")
    private Integer collectionId;

    @Column(columnDefinition = "int not null")
    private Integer price;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String category;
}
