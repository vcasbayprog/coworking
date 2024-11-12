package com.example.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coworking.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
