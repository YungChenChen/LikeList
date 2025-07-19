package com.ycc.likelist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "like_list")
@Data
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sn;

    @Column(name = "order_name")
    private Integer orderName;

    private String account;

    @Column(name = "product_no")
    private String productNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "total_fee")
    private Double totalFee;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_no", insertable = false, updatable = false)
    private Product product;
}
