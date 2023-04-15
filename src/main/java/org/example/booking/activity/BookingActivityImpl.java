package org.example.booking.activity;

import java.util.UUID;

public class BookingActivityImpl implements BookingActivity {

    @Override
    public String reserveCar(String name) {
        System.out.println(String.format("reserve car for %s", name));
        return UUID.randomUUID().toString();
    }

    @Override
    public String bookFlight(String name) {
        System.out.println(String.format("reserve flight for %s", name));
        throw new RuntimeException("Flight booking did not work");
    }

    @Override
    public String bookHotel(String name) {
        System.out.println(String.format("reserve hotel for %s", name));
        return UUID.randomUUID().toString();
    }

    @Override
    public String cancelCar(String reservationId, String name) {
        System.out.println("cancelling car reservation '" + reservationId + "' for '" + name + "'");
        return UUID.randomUUID().toString();
    }

    @Override
    public String cancelFlight(String reservationId, String name) {
        System.out.println("cancelling flight reservation '" + reservationId + "' for '" + name + "'");
        return UUID.randomUUID().toString();
    }

    @Override
    public String cancelHotel(String reservationId, String name) {
        System.out.println("cancelling hotel reservation '" + reservationId + "' for '" + name + "'");
        return UUID.randomUUID().toString();
    }
}
