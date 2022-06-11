package com.poogie.sns.user.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
import com.poogie.sns.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    public ResponseDto add(UserDto.SignUpReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }

    public ResponseDto findById(Long id) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findById(id));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("조회 성공");

        return res;
    }

    public ResponseDto findByEmail(String email) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findByEmail(email));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("조회 성공");

        return res;
    }

    public ResponseDto findByEmailAndPassword(UserDto.SignInReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findByEmailAndPassword(req.getEmail(), req.getPassword()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("조회 성공");

        return res;
    }
}
