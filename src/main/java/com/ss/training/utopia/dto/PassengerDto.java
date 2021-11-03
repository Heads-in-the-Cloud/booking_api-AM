package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    private Integer id;

    @NotBlank(message = "Passengers must be linked to a Booking ID.")
    private Integer bookingId;

    @NotBlank(message = "Passengers must have a given name.")
    @Size(max=255, message = "Given names have a max length of 255.")
    private String givenName;

    @NotBlank(message = "Passengers must have a family name.")
    @Size(max=255, message = "Family names have a max length of 255.")
    private String familyName;

    @NotBlank(message = "Passengers must have a Date of Birth.")
    private LocalDate dob;

    @NotBlank(message = "Passengers must supply a Gender (Male, Female, Other, Not Specified).")
    @Size(max=45, message = "Gender input has a max length of 45.")
    private String gender;

    @NotBlank(message = "Passengers must supply an address.")
    @Size(min=10, max=45, message = "Address length must be between 10 and 45.")
    private String address;
}
