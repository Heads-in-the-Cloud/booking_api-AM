package com.ss.training.utopia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="is_active")
    private Integer isActive;

    @Column(name="confirmation_code")
    private String confirm;

    @OneToMany(targetEntity = Passenger.class, mappedBy="bookingId", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "booking_id")
    private Payment payment;
}
