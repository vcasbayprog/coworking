package com.example.coworking.mapper;

import org.mapstruct.Mapper;

import com.example.coworking.dto.ReservationDTO;
import com.example.coworking.entity.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDTO reservationToReservationDTO(Reservation reservation);

    Reservation reservationDTOToReservation(ReservationDTO reservationDTO);

}
