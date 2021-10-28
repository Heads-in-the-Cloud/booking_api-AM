package com.ss.training.utopia.dto;

import com.ss.training.utopia.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingObjects {
    private Booking booking;
    private BookingAgent bookingAgent;
    private BookingFlight bookingFlight;
    private BookingGuest bookingGuest;
    private BookingUser bookingUser;
}
