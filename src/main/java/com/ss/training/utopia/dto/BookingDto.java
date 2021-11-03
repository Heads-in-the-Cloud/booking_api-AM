package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Integer id;

    @NotBlank(message = "You must set the booking's activation status.")
    @Min(value = 0, message = "Active status must be 1 or 0.")
    @Max(value = 1, message = "Active status must be 1 or 0.")
    private Integer isActive;

    @NotBlank(message = "You must supply a confirmation code for the booking.")
    @Size(min=10, max=10, message = "Confirmation codes must be 10 characters long.")
    private String confirmationCode;

    @NotBlank(message = "You must enter a flight ID.")
    private Integer flightId;

    @NotBlank(message = "You must enter an Agent for the booking.")
    private Integer agentId;

    private Integer userId;

    @Email(message = "Must enter a valid email address.")
    private String guestEmail;

    @Size(min=10, max=20, message = "Phone numbers should be at least 10 characters, no more than 20.")
    private String guestPhone;
}
