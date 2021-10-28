package com.ss.training.utopia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateDto {
    private Integer id;

    @NotBlank
    @Min(0)
    @Max(1)
    private Integer isActive;

    @NotBlank
    @Size(min=10, max=10)
    private String confirmationCode;

    @NotBlank
    private Integer flightId;

    @NotBlank
    private Integer userId;

    @NotBlank
    private Integer agentId;

    @NotBlank
    @Email
    private String guestEmail;

    @NotBlank
    private String guestPhone;
}
