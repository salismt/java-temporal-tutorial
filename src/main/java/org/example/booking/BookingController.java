package org.example.booking;

import org.example.booking.contract.BookingRequest;
import org.example.booking.contract.BookingResponse;
import org.example.booking.workflow.BookingWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private BookingWorkflow booking;

    public BookingController(BookingWorkflow booking) {
        this.booking = booking;
    }

    @PostMapping
    public Mono<ResponseEntity<BookingResponse>> createBooking(@RequestBody BookingRequest request) {
        BookingResponse result = booking.book(request.getName());
        return Mono.just(ResponseEntity.ok(result));
    }
}
