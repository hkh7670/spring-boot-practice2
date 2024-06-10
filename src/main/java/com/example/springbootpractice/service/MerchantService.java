package com.example.springbootpractice.service;

import com.example.springbootpractice.exception.ApiErrorException;
import com.example.springbootpractice.exception.ErrorCode;
import com.example.springbootpractice.model.entity.Merchant;
import com.example.springbootpractice.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MerchantService {

  private final MerchantRepository merchantRepository;

  @Transactional(readOnly = true)
  public Merchant getMerchant(String merchantId) {
    return merchantRepository.findByMerchantIdAndIsUsed(merchantId, true)
        .orElseThrow(() -> new ApiErrorException(ErrorCode.NOT_FOUND_MERCHANT));
  }

}
