package com.poogie.sns.room.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.room.dao.RoomImageService;
import com.poogie.sns.room.dao.RoomParticipantService;
import com.poogie.sns.room.dao.RoomService;
import com.poogie.sns.room.dto.RoomDto;
import com.poogie.sns.room.dto.RoomParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestBody RoomDto.CreateReq req) {
        ResponseDto res = roomService.add(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/invite")
    public ResponseEntity<ResponseDto> invite(@RequestBody RoomParticipantDto.InviteReq req) {
        ResponseDto res = roomParticipantService.add(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<ResponseDto> list(@PathVariable Long userId) {
        ResponseDto res = roomService.findByUserId(userId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(
            value = "/image/{roomId}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> imageDetail(@PathVariable Long roomId) throws IOException {
        byte[] image = roomImageService.findByRoomId(roomId);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
