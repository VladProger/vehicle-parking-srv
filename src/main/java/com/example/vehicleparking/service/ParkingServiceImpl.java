package com.example.vehicleparking.service;

import com.example.vehicleparking.constant.VehicleType;
import com.example.vehicleparking.dto.response.ParkingAvailabilityResponse;
import com.example.vehicleparking.dto.ParkingRecord;
import com.example.vehicleparking.dto.VehicleBillingDto;
import com.example.vehicleparking.dto.response.ParkVehicleResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final int parkingCapacity;
    private final ConcurrentMap<String, ParkingRecord> parkedVehicles = new ConcurrentHashMap<>();
    private final PriorityQueue<Integer> availableSpaces;

    public ParkingServiceImpl(@Value("${service.parking.capacity}") int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        availableSpaces = new PriorityQueue<>(parkingCapacity);
        for (int i = 1; i <= parkingCapacity; i++) {
            availableSpaces.add(i);
        }
    }

    public ParkingAvailabilityResponse getParkingAvailability() {
        return new ParkingAvailabilityResponse(availableSpaces.size(), parkedVehicles.size());
    }

    public ParkVehicleResponse parkVehicle(VehicleType vehicleType, String vehicleReg) {
        ParkingRecord parkingRecord = new ParkingRecord(vehicleReg, vehicleType, occupyAvailableSpace(), LocalDateTime.now());

        if(parkedVehicles.putIfAbsent(vehicleReg, parkingRecord) != null) {
            throw new IllegalArgumentException("Vehicle with provided registration number is already parked");
        }

        return ParkVehicleResponse.builder()
                .vehicleReg(parkingRecord.vehicleReg())
                .timeIn(parkingRecord.timeIn())
                .spaceNumber(parkingRecord.spaceNumber())
                .build();
    }

    public VehicleBillingDto billVehicleAndFreeSpace(String vehicleReg) {
        ParkingRecord parkingRecord = freeParkingSpace(vehicleReg);

        LocalDateTime timeOut = LocalDateTime.now();

        long minutes = Duration.between(parkingRecord.timeIn(), timeOut).toMinutes();
        double vehicleCharge = calculateVehicleCharge(minutes, parkingRecord.vehicleType());

        return VehicleBillingDto.builder()
                .billId(UUID.randomUUID().toString())
                .vehicleReg(vehicleReg)
                .timeOut(timeOut)
                .timeIn(parkingRecord.timeIn())
                .vehicleCharge(vehicleCharge)
                .build();
    }

    private double calculateVehicleCharge(long minutes, VehicleType vehicleType) {
        return vehicleType.getPricePerMinute() * minutes + minutes / 5;
    }

    private synchronized int occupyAvailableSpace() {
        Integer spaceNumber = availableSpaces.poll();
        if (spaceNumber == null) {
            throw new IllegalArgumentException("Parking is full");
        }

        return spaceNumber;
    }

    private synchronized ParkingRecord freeParkingSpace(String vehicleReg) {
        ParkingRecord parkingRecord = parkedVehicles.remove(vehicleReg);
        if (parkingRecord == null) {
            throw new IllegalArgumentException("Vehicle with provided registration number is no longer parked");
        }
        availableSpaces.add(parkingRecord.spaceNumber());
        return parkingRecord;
    }
}