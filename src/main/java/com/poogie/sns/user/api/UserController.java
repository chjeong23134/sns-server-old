package com.poogie.sns.user.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.user.dao.UserService;
import com.poogie.sns.user.dto.UserDto;
import com.poogie.sns.user.dto.UserImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    /*
        [ 회원가입 ]
        - email (String)
        - password (String)
        - name (String)
     */
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> signUp(@RequestBody UserDto.SignUpReq req) {
        ResponseDto res = userService.addUser(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 이메일 중복체크 ]
        - email (String)
     */
    @GetMapping("/email-duplicate-check/{email}")
    public ResponseEntity<ResponseDto> emailDuplicateCheck(@PathVariable String email) {
        ResponseDto res = userService.findUserEmail(email);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 로그인 ]
        - email (String)
        - password (String)
     */
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDto> signIn(@RequestBody UserDto.SignInReq req) {
        ResponseDto res = userService.findUserByEmailAndPassword(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 프로필 이미지 생성 또는 업데이트 ]
        - userId (String)
        - image (MultipartFile)
     */
    @PostMapping("/profile")
    public ResponseEntity<ResponseDto> profile(
            @RequestPart String userId,
            @RequestPart MultipartFile image
    ) throws IOException {
        ResponseDto res = userService.saveUserImage(Long.valueOf(userId), image);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
