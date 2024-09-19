package com.aneeq.venue_manager.repository;

import com.aneeq.venue_manager.entity.Authorizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizerRepository extends JpaRepository<Authorizer, Integer> {
}
