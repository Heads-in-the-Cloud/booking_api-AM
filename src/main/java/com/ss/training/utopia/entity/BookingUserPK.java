package com.ss.training.utopia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingUserPK implements Serializable {

    @Column(name="user_id")
    private Integer userId;

    @Column(name="booking_id")
    private Integer bookingId;
}
