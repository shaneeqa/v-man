package com.aneeq.venuemanager.repository;

import com.aneeq.venuemanager.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
