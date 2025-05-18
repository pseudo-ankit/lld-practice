package com.aks.parkinglot;

import com.aks.parkinglot.models.Ticket;
import com.aks.parkinglot.models.Vehicle;
import com.aks.parkinglot.strategies.ParkingStrategy;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class EntranceGate {
    private final ParkingSpotManagerFactory spotManagerFactory;

    private static final ParkingStrategy defaultStrategy = (vehicle, parkingSpots) ->
            parkingSpots.stream()
                    .filter(parkingSpot -> parkingSpot.getType().equals(vehicle.getVehicleType()))
                    .filter(Predicate.not(ParkingSpot::isOccupied))
                    .findFirst()
                    .orElse(null);

    public EntranceGate(ParkingSpotManagerFactory spotManagerFactory) {
        this.spotManagerFactory = spotManagerFactory;
    }

    public Ticket bookParkingSpot(Vehicle vehicle) {
        return bookParkingSpot(vehicle, defaultStrategy);
    }

    public Ticket bookParkingSpot(Vehicle vehicle, ParkingStrategy parkingStrategy) {
        var manager = spotManagerFactory.getParkingSpotManager(vehicle.getVehicleType());
        // find and assign the parking spot
        var assignedSpot = manager.assignSpot(vehicle, parkingStrategy);
        // generate the ticket and return it
        return new Ticket(LocalDateTime.now(), assignedSpot, vehicle);
    }
}
