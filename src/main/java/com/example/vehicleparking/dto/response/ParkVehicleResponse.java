package com.example.vehicleparking.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ParkVehicleResponse {
    private String vehicleReg;
    private int spaceNumber;
    private LocalDateTime timeIn;
}
