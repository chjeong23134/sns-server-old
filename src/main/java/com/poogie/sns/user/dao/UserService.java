package com.poogie.sns.user.dao;

import com.poogie.sns.user.domain.UserEntity;
import com.poogie.sns.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    public UserEntity add(UserDto.SignUpReq req) {
        return userRepository.save(req.toEntity());
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findByEmailAndPassword(UserDto.SignInReq req) {
        return userRepository.findByEmailAndPassword(req.getEmail(), req.getPassword());
    }
}
