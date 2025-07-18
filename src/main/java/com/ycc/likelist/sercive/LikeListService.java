package com.esun.productpreference.service;

import com.esun.productpreference.dto.LikeProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeListService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void addLikeProduct(LikeProductRequest request) {
        String sql = "CALL AddLikeProduct(?, ?, ?, ?)";

        jdbcTemplate.update(
            sql,
            request.getUserId(),
            request.getProductNo(),
            request.getOrderCount(),
            request.getAccount()
        );
    }
}
