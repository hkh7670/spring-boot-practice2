package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserId(String userId);

  Optional<User> findByUserIdAndIsUsed(String userId, boolean isUsed);

}
