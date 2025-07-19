package com.ycc.likelist.DTO;

import lombok.Data;

@Data
public class LikeProductResponse {

    private String productName;
    private String account;
    private Double totalAmount;
    private Double totalFee;
    private String email;
    private Integer sn;
    public Integer getSn() { return sn; }
    public void setSn(Integer sn) { this.sn = sn; }
}
