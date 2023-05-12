package com.coherent.betask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coherent.betask.model.Reservation;
import com.coherent.betask.repository.ReservationRepository;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

/**
 * This class is responsible for handling RESTful web service requests for the
 * Reservation entity.
 */
@Api(tags = "Your API")
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Retrieves all Reservation records
     * 
     * @return collection of reservation all reservation records
     */
    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations() {
        
        return ResponseEntity.status(HttpStatus.OK).body(reservationRepository.findAll());
    }

    /**
     * Saves a new Reservation record into db
     * 
     * @param reservation the new reservation data to be saved
     * @return Reservation record with specified id
     */
    @PostMapping()
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    /**
     * Saves a new Wheater record into db
     * 
     * @param reservation the new reservation data to be saved
     * @return Reservation record with specified id
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id,
            @RequestBody Reservation updatedReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setClientFullName(updatedReservation.getClientFullName());
            reservation.setRoomNumber(updatedReservation.getRoomNumber());
            reservation.setReservationDates(updatedReservation.getReservationDates());
            return ResponseEntity.ok(reservationRepository.save(reservation));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * Deletes the reservation data with the specified ID.
     *
     * @param id The ID of the reservation data to delete.
     * @return A ResponseEntity with HTTP status code 204 No Content or status code
     *         404 when was not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {

        try {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}