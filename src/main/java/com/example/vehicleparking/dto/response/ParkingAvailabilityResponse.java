package com.example.vehicleparking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingAvailabilityResponse {
    private int availableSpaces;
    private int occupiedSpaces;
}
