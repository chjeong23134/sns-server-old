package com.poogie.sns.room.api;

import com.poogie.sns.room.dao.RoomImageService;
import com.poogie.sns.room.dao.RoomParticipantService;
import com.poogie.sns.room.dao.RoomService;
import com.poogie.sns.room.domain.RoomEntity;
import com.poogie.sns.room.domain.RoomParticipantEntity;
import com.poogie.sns.room.dto.RoomDto;
import com.poogie.sns.room.dto.RoomParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomImageService roomImageService;
    @Autowired
    private RoomParticipantService roomParticipantService;

    /*
        [ 룸생성 ]
        - createUserId (Long)
        - name (String)
     */
    @PostMapping("/create")
    public ResponseEntity<RoomEntity> create(@RequestBody RoomDto.CreateReq req) {
        return new ResponseEntity<>(roomService.add(req), HttpStatus.OK);
    }

    /*
        [ 룸초대 ]
        - roomId (Long)
        - userId (Long)
     */
    @PostMapping("/invite")
    public ResponseEntity<RoomParticipantEntity> invite(@RequestBody RoomParticipantDto.InviteReq req) {
        return new ResponseEntity<>(roomParticipantService.add(req), HttpStatus.OK);
    }

    /*
        [ 룸리스트 ]
        - userId (Long)
     */
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<RoomEntity>> list(@PathVariable Long userId) {
        return new ResponseEntity<>(roomService.findByUserId(userId), HttpStatus.OK);
    }

    /*
        [ 룸이미지 ]
        - roomId (Long)
     */
    @GetMapping(
            value = "/image/{roomId}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> imageDetail(@PathVariable Long roomId) throws IOException {
        byte[] image = roomImageService.findByRoomId(roomId);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
