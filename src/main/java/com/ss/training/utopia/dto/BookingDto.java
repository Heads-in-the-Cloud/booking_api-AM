package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private Integer isActive;
    private String confirmationCode;
    private Integer flightId;
    private Integer userId;
    private Integer agentId;
    private String guestEmail;
    private String guestPhone;
}
