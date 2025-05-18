package com.aks.parkinglot;

import com.aks.parkinglot.models.Payment;
import com.aks.parkinglot.models.Ticket;
import com.aks.parkinglot.strategies.PricingStrategy;
import com.aks.parkinglot.strategies.PaymentStrategy;

public class ExitGate {
    private final ParkingSpotManagerFactory parkingSpotFactory;

    public ExitGate(ParkingSpotManagerFactory parkingSpotFactory) {
        this.parkingSpotFactory = parkingSpotFactory;
    }

    public void exitVehicle(
            Ticket ticket,
            PaymentStrategy paymentStrategy,
            PricingStrategy pricingStrategy
    ) {
        // calculate charge
        double charge = pricingStrategy.calculate(ticket);

        // TODO currency can be enum ?
        // accept payment
        paymentStrategy.acceptPayment(new Payment(charge, "USD"));

        // empty the parking spot
        var parkingManager = parkingSpotFactory.getParkingSpotManager(ticket.getVehicle().getVehicleType());
        parkingManager.freeSpot(ticket.getSpotAllocated());
    }
}
