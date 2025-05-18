package com.aks.parkinglot.strategies;

import com.aks.parkinglot.models.Ticket;

public interface PricingStrategy {
    double calculate(Ticket ticket);
}
