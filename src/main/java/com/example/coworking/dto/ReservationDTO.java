package com.example.coworking.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

     private Long id;  // ID de la reserva
    
    private LocalDateTime startTime;  // Fecha y hora de inicio
    private LocalDateTime endTime;    // Fecha y hora de fin
    
    private Long roomId;  // ID de la sala asociada
    private String roomName;  // Nombre de la sala (si lo necesitas)

    private Long userId;  // ID del usuario asociado
    private String userName;  // Nombre del usuario (si lo necesitas)


    
}
