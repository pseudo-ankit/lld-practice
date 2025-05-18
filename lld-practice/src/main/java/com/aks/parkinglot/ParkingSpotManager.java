package com.aks.parkinglot;

import com.aks.parkinglot.models.Vehicle;
import com.aks.parkinglot.strategies.ParkingStrategy;

import java.util.List;

public class ParkingSpotManager {
    private List<ParkingSpot> parkingSpots;

    public ParkingSpotManager(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    public void removeSpot(ParkingSpot spoToRemove) {
        // validations
        if (spoToRemove.isOccupied())
            throw new IllegalArgumentException("Spot already occupied, cannot remove");
        // TODO can we optimize this ?
        parkingSpots.removeIf(parkingSpot -> parkingSpot.getId().equals(spoToRemove.getId()));
    }

    public ParkingSpot assignSpot(Vehicle vehicle, ParkingStrategy parkingStrategy) {
        ParkingSpot spotAvailable = parkingStrategy.findSpot(vehicle, parkingSpots);
        spotAvailable.parkVehicle(vehicle);
        return spotAvailable;
    }

    public void freeSpot(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle();
    }
}
