package com.ycc.likelist.DTO;

import lombok.Data;

@Data
public class LikeProductRequest {
    private String userId;
    private String productNo;
    private Integer orderCount;
    private String account;
}