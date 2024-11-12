package com.example.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coworking.dto.RoomDTO;
import com.example.coworking.service.RoomService;

@RestController
@RequestMapping(value="/rooms")
public class RoomController {


    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms(){
        List<RoomDTO> rooms= roomService.getAllRooms();
        if(rooms==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomEntityById(@PathVariable Long id){
        RoomDTO room= roomService.getRoom(id);
        if(room==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(room);
        
    }

    @PostMapping()
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO){
    RoomDTO createdRoom = roomService.createRoom(roomDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);  // 201 Created
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO){
        RoomDTO updatedRoom= roomService.updateRoomDTO(id, roomDTO);
        return ResponseEntity.ok(updatedRoom);
    }



}
