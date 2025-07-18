package com.esun.productpreference.repository;

import com.esun.productpreference.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
