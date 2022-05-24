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

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(@RequestBody UserDto.SignUpReq req) {
        ResponseDto res = userService.addUser(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/email-duplicate-check/{email}")
    public ResponseEntity<ResponseDto> emailDuplicateCheck(@PathVariable String email) {
        ResponseDto res = userService.findUserEmail(email);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDto> signIn(@RequestBody UserDto.SignInReq req) {
        ResponseDto res = userService.findUserByEmailAndPassword(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
