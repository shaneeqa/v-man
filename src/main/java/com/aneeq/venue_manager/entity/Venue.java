package com.aneeq.venue_manager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "venue", schema = "vman")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
}
