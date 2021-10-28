package com.ss.training.utopia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="booking_id")
    private Integer bookingId;

    @Column(name="given_name")
    private String givenName;

    @Column(name="family_name")
    private String familyName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="gender")
    private String gender;

    @Column(name="address")
    private String address;
}
