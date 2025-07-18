package com.esun.productpreference.controller;

import com.esun.productpreference.dto.LikeProductRequest;
import com.esun.productpreference.service.LikeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class LikeListController {

    private final LikeListService likeListService;

    @PostMapping("/like")
    public ResponseEntity<String> addLikeProduct(@RequestBody LikeProductRequest request) {
        likeListService.addLikeProduct(request);
        return ResponseEntity.ok("新增成功");
    }
}
