package com.example.vehicleparking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VehicleBillingDto {
    private String billId;
    private String vehicleReg;
    private double vehicleCharge;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
}
