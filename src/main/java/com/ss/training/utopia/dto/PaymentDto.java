package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer bookingId;
    private String stripeId;
    private Integer refunded;
}
