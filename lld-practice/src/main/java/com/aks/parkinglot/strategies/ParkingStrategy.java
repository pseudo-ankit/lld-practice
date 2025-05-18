package com.aks.parkinglot.strategies;

import com.aks.parkinglot.ParkingSpot;
import com.aks.parkinglot.models.Vehicle;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot findSpot(Vehicle vehicle, List<ParkingSpot> parkingSpots);
}
