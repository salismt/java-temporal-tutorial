package org.example.booking.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import org.example.booking.contract.BookingResponse;
import org.example.booking.activity.BookingActivity;
import org.example.booking.contract.Reservation;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class BookingWorkflowImpl implements BookingWorkflow {

    private final ActivityOptions options;
    private final BookingActivity bookingActivity;

    public BookingWorkflowImpl() {
        this.options = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofHours(1))
                .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(1).build())
                .build();
        this.bookingActivity = Workflow.newActivityStub(BookingActivity.class, options);
    }

    @Override
    public BookingResponse book(String name) {
        Saga.Options sagaOptions = new Saga.Options.Builder()
                .setParallelCompensation(true)
                .build();
        Saga saga = new Saga(sagaOptions);
        try {
            String carReservationId = bookingActivity.reserveCar(name);
            saga.addCompensation(bookingActivity::cancelCar, carReservationId, name);
            Reservation carReservation = new Reservation(carReservationId, name, "car");

            String flightReservationId = bookingActivity.bookFlight(name);
            saga.addCompensation(bookingActivity::cancelFlight, flightReservationId, name);
            Reservation flightReservation = new Reservation(flightReservationId, name, "flight");


            String hotelReservationId = bookingActivity.bookHotel(name);
            saga.addCompensation(bookingActivity::cancelHotel, hotelReservationId, name);
            Reservation hotelReservation = new Reservation(hotelReservationId, name, "hotel");

            List<Reservation> reservations = Arrays.asList(carReservation, flightReservation, hotelReservation);
            return new BookingResponse(reservations);
        } catch (ActivityFailure e) {
            saga.compensate();
            throw e;
        }
    }
}
