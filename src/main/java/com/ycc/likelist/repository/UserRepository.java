package com.ycc.likelist.repository;

import com.ycc.likelist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
