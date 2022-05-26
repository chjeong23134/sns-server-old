package com.poogie.sns.user.dao;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.common.response.ResponseStatusEnum;
import com.poogie.sns.user.domain.UserImageEntity;
import com.poogie.sns.user.dto.UserDto;
import com.poogie.sns.user.dto.UserImageDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    public ResponseDto addUser(UserDto.SignUpReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.save(req.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }

    public ResponseDto findUserById(Long id) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findById(id));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("찾기 성공");

        return res;
    }

    public ResponseDto findUserByEmail(String email) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findByEmail(email));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("찾기 성공");

        return res;
    }

    public ResponseDto findUserByEmailAndPassword(UserDto.SignInReq req) {
        ResponseDto res = new ResponseDto();

        res.setData(userRepository.findByEmailAndPassword(req.getEmail(), req.getPassword()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("찾기 성공");

        return res;
    }

    public ResponseDto saveUserImage(Long userId, MultipartFile image) throws IOException {
        ResponseDto res = new ResponseDto();

        // 파일명 중복방지 date 출력
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = now.format(dateTimeFormatter);

        // 사진 저장폴더 체크
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        String path = "images" + File.separator + File.separator;

        File folder = new File(path);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 파일 확장자 추출
        String imageExt = "." + FilenameUtils.getExtension(image.getOriginalFilename());

        // 파일명 중복방지 이름에 나노초 추가
        String imageName = System.nanoTime() + currentDate + imageExt;

        // 파일 저장
        folder = new File(absolutePath + path + File.separator + imageName);
        image.transferTo(folder);

        // 기존 프로필이 있다면 업데이트
        UserImageEntity userImage = userImageRepository.findByUserId(userId);
        if (userImage != null) {
            userImage.pathModify(folder.getPath());

            res.setData(userImageRepository.save(userImage));
            res.setStatus(ResponseStatusEnum.OK);
            res.setMessage("수정 성공");

            return res;
        }

        // 기존 프로필이 없다면 생성
        UserImageDto imageDto = UserImageDto.builder()
                .userId(userId)
                .path(folder.getPath())
                .build();

        res.setData(userImageRepository.save(imageDto.toEntity()));
        res.setStatus(ResponseStatusEnum.OK);
        res.setMessage("생성 성공");

        return res;
    }
}
