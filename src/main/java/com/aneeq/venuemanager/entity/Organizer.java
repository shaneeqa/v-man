package com.aneeq.venuemanager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organizer", schema = "vman")
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    /**
     *     //private String details;
     * details meant by the basic description that the organizer can be identified.
     * e.g: if he's a student, id should be replaced with index number, so all the related details will be mapped.
     *
     * need more analysis on the details for other persons
     */
}
