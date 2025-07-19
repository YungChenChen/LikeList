package com.ycc.likelist.repository;

import com.ycc.likelist.model.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeListRepository extends JpaRepository<LikeList, Integer> {
}
