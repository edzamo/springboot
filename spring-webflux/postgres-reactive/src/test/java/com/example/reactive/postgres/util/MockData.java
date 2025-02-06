package com.example.reactive.postgres.util;

import com.example.reactive.postgres.model.Vehicle;

public class MockData {
  public static Vehicle getVehicle() {
    return Vehicle.builder()
        .id(1L)
        .model("IONIQ")
        .cubicCentimeters(1.8)
        .release(2)
        .build();
  }
}
