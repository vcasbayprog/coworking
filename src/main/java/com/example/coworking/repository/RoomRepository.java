package com.example.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coworking.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {

}
