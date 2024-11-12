package com.example.coworking.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.coworking.dto.RoomDTO;
import com.example.coworking.entity.Reservation;
import com.example.coworking.entity.Room;

public class RoomMapper {



    private RoomMapper(){

    }


    //Metodo para convertir Room a RoomDTO
    public static RoomDTO toRoomDTO(Room room){
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setName(room.getName());
        dto.setCapacity(room.getCapacity());
        dto.setAvailable(room.getAvailable());

        List<Long> reservationIds= room.getReservations()
                                        .stream()
                                        .map(Reservation::getId)
                                        .collect(Collectors.toList());

        dto.setReservationsIds(reservationIds);
        
        return dto;
    }

    //Metodo para convertir RoomDTO a Room

    public static Room toRoomEntity(RoomDTO dto) {
        Room room = new Room();
        room.setId(dto.getId());
        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setAvailable(dto.getAvailable());
        return room;
    }

}
