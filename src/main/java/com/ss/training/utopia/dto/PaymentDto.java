package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    @NotBlank(message = "Payments must be linked to a Booking ID.")
    private Integer bookingId;

    @NotBlank(message = "Payments must supply a stripe ID.")
    @Size(max = 255, message = "Stripe ID must be less than 255 characters.")
    private String stripeId;

    @NotBlank(message = "Please supply 0 or 1 for the refund status.")
    @Min(value = 0, message = "Refunded status must be 1 or 0.")
    @Max(value = 1, message = "Refunded status must be 1 or 0.")
    private Integer refunded;
}
