package com.example.reactive.mongo.util;

import com.example.reactive.mongo.model.Vehicle;

public class MockData {
  public static Vehicle getVehicle() {
    return Vehicle.builder()
        .id("66956a9ddc88461a519b1bcb")
        .model("IONIQ")
        .cubicCentimeters(1.8)
        .release(2)
        .build();
  }
}
