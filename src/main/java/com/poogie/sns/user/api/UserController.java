package com.poogie.sns.user.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.user.dao.UserService;
import com.poogie.sns.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity<ResponseDto> userAdd(@RequestBody UserDto.AddReq req) {
        ResponseDto res = userService.addUser(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
