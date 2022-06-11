package com.poogie.sns.room.dao;

import com.poogie.sns.room.domain.RoomImageEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class RoomImageService {
    @Autowired
    private RoomImageRepository roomImageRepository;

    public byte[] findByRoomId(Long roomId) throws IOException {
        RoomImageEntity roomImageEntity = roomImageRepository.findByRoomId(roomId);

        if(roomImageEntity == null) {
            InputStream imageStream = new FileInputStream("/Users/jeongchanhee/Desktop/sns-server/images/default_room_image.jpeg");
            byte[] image = IOUtils.toByteArray(imageStream);
            imageStream.close();

            return image;
        }

        InputStream imageStream = new FileInputStream(roomImageEntity.getPath());
        byte[] image = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return image;
    }
}
