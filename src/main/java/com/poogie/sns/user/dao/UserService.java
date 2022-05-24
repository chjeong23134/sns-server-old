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

    public ResponseDto addUser(UserDto.AddReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("성공");

        return res;
    }

    public ResponseDto findUserEmail(String email) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findByEmail(email));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("성공");

        return res;
    }
}
