
package com.coherent.betask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity of a reservation
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "client_full_name")
    private String clientFullName;

    @Column(name = "room_number")
    private Integer roomNumber;

    @ElementCollection
    @CollectionTable(name = "reservation_dates", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "date")
    private List<LocalDate> reservationDates;
}