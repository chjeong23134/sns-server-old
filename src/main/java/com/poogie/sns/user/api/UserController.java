package com.poogie.sns.user.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.user.dao.UserService;
import com.poogie.sns.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        ResponseDto res = userService.add(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 이메일 중복체크 ]
        - email (String)
     */
    @GetMapping("/email-duplicate-check/{email}")
    public ResponseEntity<ResponseDto> emailDuplicateCheck(@PathVariable String email) {
        ResponseDto res = userService.findByEmail(email);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 로그인 ]
        - email (String)
        - password (String)
     */
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseDto> signIn(@RequestBody UserDto.SignInReq req) {
        ResponseDto res = userService.findByEmailAndPassword(req);

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
        ResponseDto res = userService.saveImage(Long.valueOf(userId), image);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 유저 정보 ]
        - userId (Long)
     */
    @GetMapping("/detail/{userId}")
    public ResponseEntity<ResponseDto> detail(@PathVariable Long userId) {
        ResponseDto res = userService.findById(userId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /*
        [ 유저 이미지 ]
        - userId (Long)
     */
    @GetMapping(
            value = "/image/{userId}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> imageDetail(@PathVariable Long userId) throws IOException {
        return new ResponseEntity<>(userService.findImageByUserId(userId), HttpStatus.OK);
    }
}
