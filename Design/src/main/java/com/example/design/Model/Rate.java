package com.example.design.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private int rating;

//    @Column(columnDefinition = "varchar(20) not null")
//    private String type;

    @Column(columnDefinition = "int ")
    private Integer customerId;

    @Column(columnDefinition = "int")
    private Integer serviceId;

    @Column(columnDefinition = "varchar(300) not null ")
    private String feedback;

}
