package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Integer id;
    private Integer bookingId;
    private String givenName;
    private String familyName;
    private LocalDate dob;
    private String gender;
    private String address;
}
