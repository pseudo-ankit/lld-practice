package com.aks.parkinglot;

import com.aks.parkinglot.models.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingSpotManagerFactory {
    private final Map<VehicleType, ParkingSpotManager> spotManagerRegistry = new HashMap<>();

    public void registerManager(VehicleType type, ParkingSpotManager manager) {
        spotManagerRegistry.put(type, manager);
    }

    public ParkingSpotManager getParkingSpotManager(VehicleType type) {
        return spotManagerRegistry.get(type);
    }
}
