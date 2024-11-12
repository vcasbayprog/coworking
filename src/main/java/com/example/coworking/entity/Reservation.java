package com.example.coworking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime; //Fecha y hora de inicio
    private LocalDateTime endTime;   //Fecha y hora de fin


    //Relacion con Room(una reserva está asociada a una sala)
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;
    
    

    //Relacion con User(una reserva está asociada a un usuario)
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    

}
