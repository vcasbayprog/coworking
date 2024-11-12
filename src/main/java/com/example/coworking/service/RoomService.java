package com.example.coworking.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coworking.dto.RoomDTO;
import com.example.coworking.entity.Room;
import com.example.coworking.mapper.RoomMapper;
import com.example.coworking.repository.RoomRepository;

@Service
public class RoomService {



    @Autowired
    private RoomRepository roomRepository;

    // crear una nueva room
    public RoomDTO createRoom(RoomDTO roomDTO) {
        try {
            Room room = RoomMapper.toRoomEntity(roomDTO);
            Room savedRoom = roomRepository.save(room);
            return RoomMapper.toRoomDTO(savedRoom);
        } catch (Exception e) {
            System.err.println("Error al guardar la sala: " + e.getMessage());
            throw e;
        }
    }
    
    

    // buscar una room por id
    public RoomDTO getRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return RoomMapper.toRoomDTO(room);
    }

    // buscar todas las rooms
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(RoomMapper::toRoomDTO)
                .collect(Collectors.toList());
    }

    // borrar una room
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    //actualizar una room
    public RoomDTO updateRoomDTO(Long id, RoomDTO roomDTO){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setName(roomDTO.getName());
        room.setCapacity(roomDTO.getCapacity());
        room.setAvailable(roomDTO.getAvailable());

        Room updatedRoom = roomRepository.save(room);
        return RoomMapper.toRoomDTO(updatedRoom);
    }

}
