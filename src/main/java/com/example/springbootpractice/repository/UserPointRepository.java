package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.entity.UserPoint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointRepository extends JpaRepository<UserPoint, Long> {

  List<UserPoint> findByUserSeq(long userSeq);

}
