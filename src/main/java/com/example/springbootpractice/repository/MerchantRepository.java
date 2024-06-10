package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.entity.Merchant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
  Optional<Merchant> findByMerchantIdAndIsUsed(String merchantId, boolean isUsed);
}
