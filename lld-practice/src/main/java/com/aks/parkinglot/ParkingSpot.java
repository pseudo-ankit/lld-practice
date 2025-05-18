package com.aks.parkinglot;

import com.aks.parkinglot.models.Vehicle;
import com.aks.parkinglot.models.VehicleType;

import java.util.random.RandomGenerator;

public class ParkingSpot {
    private Long id;
    private VehicleType type;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(
            VehicleType type,
            boolean isOccupied,
            Vehicle vehicle
    ) {
        this.id = RandomGenerator.getDefault().nextLong();
        this.type = type;
        this.isOccupied = isOccupied;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public static ParkingSpotBuilder builder() {
        return new ParkingSpotBuilder();
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", type=" + type +
                ", isOccupied=" + isOccupied +
                ", vehicle=" + vehicle +
                '}';
    }

    public void parkVehicle(Vehicle vehicle) {
        isOccupied = true;
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        isOccupied = false;
        this.vehicle = null;
    }

    public static class ParkingSpotBuilder {
        private VehicleType type;
        private boolean isOccupied;
        private Vehicle vehicle;

        public ParkingSpotBuilder type(VehicleType type) {
            this.type = type;
            return this;
        }

        public ParkingSpotBuilder isOccupied(boolean isOccupied) {
            this.isOccupied = isOccupied;
            return this;
        }

        public ParkingSpotBuilder vehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public ParkingSpot build() {
            return new ParkingSpot(type, isOccupied, vehicle);
        }
    }

}