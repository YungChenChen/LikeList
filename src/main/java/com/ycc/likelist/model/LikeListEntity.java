package com.esun.productpreference.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LikeList")
@Data
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sn;

    private Integer orderName;

    private String account;

    private Double totalFee;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productNo")
    private Product product;
}
