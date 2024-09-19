package com.aneeq.venue_manager.repository;

import com.aneeq.venue_manager.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
