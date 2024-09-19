package com.aneeq.venue_manager.entity;

import com.aneeq.venue_manager.enumeration.StatusCodeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking", schema = "vman")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String eventName;
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
    @ManyToOne
    @JoinColumn(name = "authorizer_id")
    private Authorizer authorizer;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    private LocalDateTime timeslot;
    private StatusCodeEnum status;
}
