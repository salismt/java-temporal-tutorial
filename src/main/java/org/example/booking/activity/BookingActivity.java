package org.example.booking.activity;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface BookingActivity {

    String reserveCar(String name);

    String bookFlight(String name);

    String bookHotel(String name);

    String cancelCar(String reservationId, String name);
    String cancelFlight(String reservationId, String name);
    String cancelHotel(String reservationId, String name);
}
