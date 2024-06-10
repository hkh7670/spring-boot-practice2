package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.entity.UserPaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentHistoryRepository extends JpaRepository<UserPaymentHistory, Long> {

}
