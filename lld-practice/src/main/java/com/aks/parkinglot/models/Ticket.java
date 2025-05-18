package com.aks.parkinglot.models;

import com.aks.parkinglot.ParkingSpot;

import java.time.LocalDateTime;

public class Ticket {
    private String ticketNumber;
    private LocalDateTime entryTime;
    private ParkingSpot spotAllocated;
    private Vehicle vehicle;

    public Ticket(LocalDateTime entryTime, ParkingSpot spotAllocated, Vehicle vehicle) {
        this.ticketNumber = generateTktNum(entryTime, spotAllocated, vehicle);
        this.entryTime = entryTime;
        this.spotAllocated = spotAllocated;
        this.vehicle = vehicle;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public ParkingSpot getSpotAllocated() {
        return spotAllocated;
    }

    public void setSpotAllocated(ParkingSpot spotAllocated) {
        this.spotAllocated = spotAllocated;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private String generateTktNum(LocalDateTime entryTime, ParkingSpot spotAllocated, Vehicle vehicle) {
        return spotAllocated.getId().toString() + "-" + vehicle.getNumber() + "-" + entryTime.toString();
    }
}
