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
@Table(name = "booking_payment")
public class Payment {
    @Id
    @Column(name="booking_id")
    private Integer booking;

    @Column(name="stripe_id")
    private String stripeId;

    @Column(name="refunded")
    private Integer refunded;
}
