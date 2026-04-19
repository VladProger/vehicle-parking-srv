package com.example.vehicleparking.service;

import com.example.vehicleparking.constant.VehicleType;
import com.example.vehicleparking.dto.response.ParkVehicleResponse;
import com.example.vehicleparking.dto.response.ParkingAvailabilityResponse;
import com.example.vehicleparking.dto.VehicleBillingDto;

public interface ParkingService {
    ParkingAvailabilityResponse getParkingAvailability();
    ParkVehicleResponse parkVehicle(VehicleType vehicleType, String vehicleReg);
    VehicleBillingDto billVehicleAndFreeSpace(String vehicleReg);
}
