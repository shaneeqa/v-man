package com.aneeq.venuemanager.repository;

import com.aneeq.venuemanager.entity.Authorizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizerRepository extends JpaRepository<Authorizer, Integer> {
}
