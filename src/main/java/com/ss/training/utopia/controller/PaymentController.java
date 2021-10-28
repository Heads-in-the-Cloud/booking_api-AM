package com.ss.training.utopia.controller;

import com.ss.training.utopia.dto.PaymentDto;
import com.ss.training.utopia.entity.Payment;
import com.ss.training.utopia.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    // construction
    private final PaymentService service;
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = service.getAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentByBooking(@PathVariable int id) {
        return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody PaymentDto dto) {
        Payment payment = service.add(dto);
        URI uri = URI.create("/api/v1/payments/" + payment.getBooking());
        return ResponseEntity.created(uri).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@RequestBody PaymentDto dto) {
        service.update(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
