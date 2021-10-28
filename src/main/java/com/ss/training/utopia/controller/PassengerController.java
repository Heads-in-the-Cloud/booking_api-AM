package com.ss.training.utopia.controller;

import com.ss.training.utopia.dto.PassengerDto;
import com.ss.training.utopia.entity.Passenger;
import com.ss.training.utopia.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    // construction
    private final PassengerService service;
    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = service.getAll();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<List<Passenger>> getPassengersByBooking(@PathVariable int id) {
        List<Passenger> passengers = service.getAllByBookingId(id);
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerDto dto) {
        Passenger passenger = service.add(dto);
        URI uri = URI.create("/api/v1/passengers/" + passenger.getId());
        return ResponseEntity.created(uri).body(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@RequestBody PassengerDto dto) {
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
