package com.ycc.likelist.controller;

import com.ycc.likelist.DTO.LikeProductRequest;
import com.ycc.likelist.DTO.LikeProductResponse;
import com.ycc.likelist.service.LikeListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ycc.likelist.model.User;
import com.ycc.likelist.model.Product;
import com.ycc.likelist.repository.UserRepository;
import com.ycc.likelist.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LikeListController {

    private final LikeListService likeListService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // 新增喜好金融商品
    @PostMapping("/like")
    public ResponseEntity<String> addLikeProduct(@RequestBody LikeProductRequest request) {
        try {
            log.info("Received request to add like product: {}", request);
            likeListService.addLikeProduct(request);
            return ResponseEntity.ok("喜好商品新增成功");
        } catch (Exception e) {
            log.error("Error adding like product: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("新增失敗: " + e.getMessage());
        }
    }

    // 查詢喜好金融商品清單
    @GetMapping("/likes")
    public ResponseEntity<List<LikeProductResponse>> getLikeList() {
        try {
            log.info("Received request to get like list");
            List<LikeProductResponse> response = likeListService.getLikeList();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error getting like list: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 刪除喜好金融商品資訊
    @DeleteMapping("/like/{sn}")
    public ResponseEntity<String> deleteLikeProduct(@PathVariable Integer sn) {
        try {
            log.info("Received request to delete like product with SN: {}", sn);
            likeListService.deleteLikeProduct(sn);
            return ResponseEntity.ok("喜好商品刪除成功");
        } catch (Exception e) {
            log.error("Error deleting like product: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("刪除失敗: " + e.getMessage());
        }
    }

    // 更改喜好金融商品資訊
    @PutMapping("/like/{sn}")
    public ResponseEntity<String> updateLikeProduct(@PathVariable Integer sn, @RequestBody LikeProductRequest request) {
        try {
            log.info("Received request to update like product with SN: {}", sn);
            likeListService.updateLikeProduct(sn, request);
            return ResponseEntity.ok("喜好商品更新成功");
        } catch (Exception e) {
            log.error("Error updating like product: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("更新失敗: " + e.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
