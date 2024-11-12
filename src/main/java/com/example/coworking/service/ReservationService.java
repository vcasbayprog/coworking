package com.example.coworking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coworking.dto.ReservationDTO;
import com.example.coworking.entity.Reservation;
import com.example.coworking.entity.Room;
import com.example.coworking.entity.User;
import com.example.coworking.mapper.ReservationMapper;
import com.example.coworking.repository.ReservationRepository;
import com.example.coworking.repository.RoomRepository;
import com.example.coworking.repository.UserRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    // crear una nueva reserva
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        try {
            // Convertir el DTO a entidad
            Reservation reservation = reservationMapper.reservationDTOToReservation(reservationDTO);

            // Guardar la entidad en la base de datos
            Reservation savedReservation = reservationRepository.save(reservation);

            // Convertir la entidad a DTO
            return reservationMapper.reservationToReservationDTO(savedReservation);

        } catch (Exception e) {
            System.err.println("Error al guardar la reserva: " + e.getMessage());
            throw e;
        }
    }

    // buscar una reserva por id
    public ReservationDTO getReservation(Long id) {
        try {
            // Obtener la entidad de la base de datos
            Reservation reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reservation not found"));
            return reservationMapper.reservationToReservationDTO(reservation);

        } catch (Exception e) {
            System.err.println("Error al buscar la reserva: " + e.getMessage());
            throw e;
        }
    }

    //buscar todas las reservas
    public List<ReservationDTO> getAllReservations() {
        try {
            List<Reservation> reservations = reservationRepository.findAll();
            return reservations.stream()
                    .map(reservationMapper::reservationToReservationDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener las reservas: " + e.getMessage());
            throw e;
        }
       
    }

    //borrar una reserva 
    public void deleteRoom(Long id){
        try {
            reservationRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error al borrar la reserva: " + e.getMessage());
            throw e;
        }
    }

    // Actualizar una reserva existente
    public ReservationDTO updateReservationDTO(Long id, ReservationDTO reservationDTO) {
        try {
            Reservation reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Reservation not found"));

            // Actualizar las fechas
            reservation.setStartTime(reservationDTO.getStartTime());
            reservation.setEndTime(reservationDTO.getEndTime());

            // Obtener y establecer la sala
            if (reservationDTO.getRoomId() != null) {
                Room room = roomRepository.findById(reservationDTO.getRoomId())
                        .orElseThrow(() -> new RuntimeException("Room not found"));
                reservation.setRoom(room);
            }

            // Obtener y establecer el usuario
            if (reservationDTO.getUserId() != null) {
                User user = userRepository.findById(reservationDTO.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                reservation.setUser(user);
            }

            // Guardar la reserva actualizada
            reservation = reservationRepository.save(reservation);

            // Convertir a DTO y devolver
            return reservationMapper.reservationToReservationDTO(reservation);

        } catch (Exception e) {
            System.err.println("Error al actualizar la reserva: " + e.getMessage());
            throw e;
        }
    }
}
