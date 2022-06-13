package com.poogie.sns.user.dao;

import com.poogie.sns.user.domain.UserImageEntity;
import com.poogie.sns.user.dto.UserImageDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserImageService {
    @Autowired
    private UserImageRepository userImageRepository;

    public UserImageEntity save(Long userId, MultipartFile image) throws IOException {
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

            return userImageRepository.save(userImage);
        }

        // 기존 프로필이 없다면 생성
        UserImageDto imageDto = UserImageDto.builder()
                .userId(userId)
                .path(folder.getPath())
                .build();

        return userImageRepository.save(imageDto.toEntity());
    }

    public byte[] findByUserId(Long userId) throws IOException {
        UserImageEntity userImage = userImageRepository.findByUserId(userId);

        if(userImage == null) {
            InputStream imageStream = new FileInputStream("/Users/jeongchanhee/Desktop/sns-server/images/default_user_image.jpeg");
            byte[] image = IOUtils.toByteArray(imageStream);
            imageStream.close();

            return image;
        }

        InputStream imageStream = new FileInputStream(userImage.getPath());
        byte[] image = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return image;
    }
}
