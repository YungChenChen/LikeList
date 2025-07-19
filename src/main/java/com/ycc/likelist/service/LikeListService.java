package com.ycc.likelist.service;

import com.ycc.likelist.DTO.LikeProductRequest;
import com.ycc.likelist.DTO.LikeProductResponse;
import com.ycc.likelist.model.LikeList;
import com.ycc.likelist.repository.LikeListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeListService {

    private final JdbcTemplate jdbcTemplate;
    private final LikeListRepository likeListRepository;

    @Transactional
    public void addLikeProduct(LikeProductRequest request) {
        try {
            log.info("Adding like product: {}", request);
            String sql = "CALL add_like_product(?, ?, ?, ?)";

            jdbcTemplate.update(
                sql,
                request.getUserId(),
                request.getProductNo(),
                request.getOrderCount(),
                request.getAccount()
            );
            log.info("Successfully added like product");
        } catch (Exception e) {
            log.error("Error adding like product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add like product: " + e.getMessage(), e);
        }
    }

    public List<LikeProductResponse> getLikeList() {
        try {
            log.info("Fetching like list");
            
            // 先檢查資料庫中是否有數據
            long count = likeListRepository.count();
            log.info("Total like products in database: {}", count);
            
            List<LikeList> likeEntities = likeListRepository.findAll();
            log.info("Retrieved {} like entities", likeEntities.size());
            
            List<LikeProductResponse> responses = likeEntities.stream().map(entity -> {
                log.debug("Processing entity: SN={}, ProductNo={}, UserID={}", 
                         entity.getSn(), entity.getProductNo(), entity.getUserId());
                
                LikeProductResponse res = new LikeProductResponse();
                
                if (entity.getProduct() != null) {
                    res.setProductName(entity.getProduct().getProductName());
                    log.debug("Product name: {}", entity.getProduct().getProductName());
                } else {
                    log.warn("Product is null for entity SN: {}", entity.getSn());
                    res.setProductName("未知產品");
                }
                
                res.setAccount(entity.getAccount());
                res.setTotalAmount(entity.getTotalAmount());
                res.setTotalFee(entity.getTotalFee());
                res.setSn(entity.getSn()); // 新增這行
                
                if (entity.getUser() != null) {
                    res.setEmail(entity.getUser().getEmail());
                    log.debug("User email: {}", entity.getUser().getEmail());
                } else {
                    log.warn("User is null for entity SN: {}", entity.getSn());
                    res.setEmail("未知用戶");
                }
                
                log.debug("Created response: {}", res);
                return res;
            }).collect(Collectors.toList());
            
            log.info("Successfully created {} responses", responses.size());
            return responses;
        } catch (Exception e) {
            log.error("Error fetching like list: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch like list: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void deleteLikeProduct(Integer sn) {
        try {
            log.info("Deleting like product with SN: {}", sn);
            if (!likeListRepository.existsById(sn)) {
                throw new RuntimeException("Like product with SN " + sn + " not found");
            }
            likeListRepository.deleteById(sn);
            log.info("Successfully deleted like product with SN: {}", sn);
        } catch (Exception e) {
            log.error("Error deleting like product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete like product: " + e.getMessage(), e);
        }
    }

    @Transactional
    public void updateLikeProduct(Integer sn, LikeProductRequest request) {
        try {
            log.info("Updating like product with SN: {}", sn);
            if (!likeListRepository.existsById(sn)) {
                throw new RuntimeException("Like product with SN " + sn + " not found");
            }
            
            String sql = "CALL update_like_product(?, ?, ?, ?, ?)";

            jdbcTemplate.update(
                sql,
                sn,
                request.getUserId(),
                request.getProductNo(),
                request.getOrderCount(),
                request.getAccount()
            );
            log.info("Successfully updated like product with SN: {}", sn);
        } catch (Exception e) {
            log.error("Error updating like product: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update like product: " + e.getMessage(), e);
        }
    }
}
