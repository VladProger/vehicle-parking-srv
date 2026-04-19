package com.example.vehicleparking.dto;

import com.example.vehicleparking.constant.VehicleType;

import java.time.LocalDateTime;

public record ParkingRecord(String vehicleReg, VehicleType vehicleType, int spaceNumber, LocalDateTime timeIn) {
}
