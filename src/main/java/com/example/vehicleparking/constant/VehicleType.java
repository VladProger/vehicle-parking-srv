package com.example.vehicleparking.constant;

import lombok.Getter;

@Getter
public enum VehicleType {
    SMALL_CAR(1, 0.1),
    MEDIUM_CAR(2, 0.2),
    LARGE_CAR(3, 0.4);

    private final int value;
    private final double pricePerMinute;

    VehicleType(int value, double pricePerMinute) {
        this.value = value;
        this.pricePerMinute = pricePerMinute;
    }

    public static VehicleType getByValue(int value) {
        for (VehicleType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + value);
    }
}
