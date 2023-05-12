package com.coherent.betask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coherent.betask.model.Reservation;


/**
 * Repository of Reservation
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}