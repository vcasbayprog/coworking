package com.example.coworking.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Long id;
    private String name;
    private Integer capacity;
    private Boolean available;

    private List<Long> reservationsIds;




}
