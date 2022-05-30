package com.poogie.sns.room.api;

import com.poogie.sns.common.response.ResponseDto;
import com.poogie.sns.room.dao.RoomService;
import com.poogie.sns.room.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestBody RoomDto.CreateReq req) {
        ResponseDto res = roomService.add(req);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
