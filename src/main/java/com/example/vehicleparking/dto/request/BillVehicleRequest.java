package com.example.vehicleparking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillVehicleRequest {
    @NotBlank
    private String vehicleReg;
}
