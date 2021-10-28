package com.ss.training.utopia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="booking_guest")
public class BookingGuest {

    @Id
    @Column(name="booking_id")
    private Integer bookingId;

    @Column(name="contact_email")
    private String email;

    @Column(name="contact_phone")
    private String phone;
}
