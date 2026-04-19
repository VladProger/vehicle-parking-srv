package com.example.vehicleparking.controller;

import com.example.vehicleparking.constant.VehicleType;
import com.example.vehicleparking.dto.request.BillVehicleRequest;
import com.example.vehicleparking.dto.request.ParkVehicleRequest;
import com.example.vehicleparking.dto.response.ParkVehicleResponse;
import com.example.vehicleparking.dto.response.ParkingAvailabilityResponse;
import com.example.vehicleparking.dto.VehicleBillingDto;
import com.example.vehicleparking.service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping
    public ParkingAvailabilityResponse getParkingAvailability() {
        return parkingService.getParkingAvailability();
    }

    @PostMapping
    public ParkVehicleResponse parkVehicle(@RequestBody @Valid ParkVehicleRequest request) {
        return parkingService.parkVehicle(VehicleType.getByValue(request.getVehicleType()), request.getVehicleReg());
    }

    @PostMapping(path = "bill")
    public VehicleBillingDto billVehicleAndFreeSpace(@RequestBody @Valid BillVehicleRequest request) {
        return parkingService.billVehicleAndFreeSpace(request.getVehicleReg());
    }
}
