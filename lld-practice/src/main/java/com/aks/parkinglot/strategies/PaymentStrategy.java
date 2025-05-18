package com.aks.parkinglot.strategies;

import com.aks.parkinglot.models.Payment;

public interface PaymentStrategy {
    void acceptPayment(Payment payment);
}
