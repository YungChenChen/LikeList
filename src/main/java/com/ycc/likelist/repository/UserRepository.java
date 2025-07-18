package com.esun.productpreference.repository;

import com.esun.productpreference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
