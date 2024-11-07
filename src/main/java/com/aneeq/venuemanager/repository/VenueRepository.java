package com.aneeq.venuemanager.repository;

import com.aneeq.venuemanager.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    List<Venue> findByNameIgnoreCaseContaining(String name);
}
