package com.poogie.sns.user.api;

import com.poogie.sns.user.dao.UserImageService;
import com.poogie.sns.user.dao.UserService;
import com.poogie.sns.user.domain.UserEntity;
import com.poogie.sns.user.domain.UserImageEntity;
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
    @Autowired
    private UserImageService userImageService;

    /*
        [ 회원가입 ]
        - email (String)
        - password (String)
        - name (String)
     */
    @PostMapping("/sign-up")
    public ResponseEntity<UserEntity> signUp(@RequestBody UserDto.SignUpReq req) {
        return new ResponseEntity<>(userService.add(req), HttpStatus.OK);
    }

    /*
        [ 이메일 중복체크 ]
        - email (String)
     */
    @GetMapping("/email-check/{email}")
    public ResponseEntity<UserEntity> emailCheck(@PathVariable String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    /*
        [ 로그인 ]
        - email (String)
        - password (String)
     */
    @PostMapping("/sign-in")
    public ResponseEntity<UserEntity> signIn(@RequestBody UserDto.SignInReq req) {
        return new ResponseEntity<>(userService.findByEmailAndPassword(req), HttpStatus.OK);
    }

    /*
        [ 프로필 이미지 생성 또는 업데이트 ]
        - userId (String)
        - image (MultipartFile)
     */
    @PostMapping("/profile")
    public ResponseEntity<UserImageEntity> profile(
            @RequestPart String userId,
            @RequestPart MultipartFile image
    ) throws IOException {
        return new ResponseEntity<>(userImageService.save(Long.valueOf(userId), image), HttpStatus.OK);
    }

    /*
        [ 유저 정보 ]
        - userId (Long)
     */
    @GetMapping("/detail/{userId}")
    public ResponseEntity<UserEntity> detail(@PathVariable Long userId) {

        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
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
        return new ResponseEntity<>(userImageService.findByUserId(userId), HttpStatus.OK);
    }
}
