package com.ss.training.utopia.controller;

import com.ss.training.utopia.dto.BookingDto;
import com.ss.training.utopia.entity.Booking;
import com.ss.training.utopia.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    // construction
    private final BookingService service;
    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/status")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Healthy");
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = service.getAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDto dto) {
        Booking booking = service.add(dto);
        URI uri = URI.create("/api/v1/bookings/" + booking.getId());
        return ResponseEntity.created(uri).body(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody BookingDto dto) {
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
