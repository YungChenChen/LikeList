package com.esun.productpreference.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    private String productName;

    private Double price;

    private Double feeRate;
}
