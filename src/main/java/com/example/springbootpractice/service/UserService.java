package com.example.springbootpractice.service;

import com.example.springbootpractice.exception.ApiErrorException;
import com.example.springbootpractice.exception.ErrorCode;
import com.example.springbootpractice.model.entity.User;
import com.example.springbootpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public User getUser(String userId) {
    return userRepository.findByUserIdAndIsUsed(userId, true)
        .orElseThrow(() -> new ApiErrorException(ErrorCode.NOT_FOUND_USER));
  }

}
