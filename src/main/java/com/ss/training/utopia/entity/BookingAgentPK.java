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
public class BookingAgentPK implements Serializable {

    @Column(name="agent_id")
    private Integer agentId;

    @Column(name="booking_id")
    private Integer bookingId;
}
