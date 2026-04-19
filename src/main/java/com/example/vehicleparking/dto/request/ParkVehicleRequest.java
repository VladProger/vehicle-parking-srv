package com.example.vehicleparking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkVehicleRequest {
    private int vehicleType;
    @NotBlank
    private String vehicleReg;
}
