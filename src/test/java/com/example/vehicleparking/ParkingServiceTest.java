package com.example.vehicleparking;

import com.example.vehicleparking.constant.VehicleType;
import com.example.vehicleparking.dto.response.ParkVehicleResponse;
import com.example.vehicleparking.dto.response.ParkingAvailabilityResponse;
import com.example.vehicleparking.service.ParkingService;
import com.example.vehicleparking.service.ParkingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingServiceTest {

    private final Integer parkingCapacity = 25;
    private ParkingService parkingService;

    @BeforeEach
    void setUp() {
        parkingService = new ParkingServiceImpl(parkingCapacity);
    }

    @Test
    void getParkingAvailability_init() {
        ParkingAvailabilityResponse parkingAvailabilityResponse = parkingService.getParkingAvailability();
        assertEquals(parkingCapacity, parkingAvailabilityResponse.getAvailableSpaces());
        assertEquals(0, parkingAvailabilityResponse.getOccupiedSpaces());
    }

    @Test
    void parkVehicleTest() {
        final String vehReg = "test";
        ParkVehicleResponse parkVehicleResponse = parkingService.parkVehicle(VehicleType.SMALL_CAR, vehReg);
        assertEquals(vehReg, parkVehicleResponse.getVehicleReg());
        assertEquals(1, parkVehicleResponse.getSpaceNumber());
        assertNotNull(parkVehicleResponse.getTimeIn());
    }


}
