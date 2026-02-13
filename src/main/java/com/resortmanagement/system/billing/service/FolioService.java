package com.resortmanagement.system.billing.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resortmanagement.system.billing.entity.Folio;
import com.resortmanagement.system.billing.entity.FolioStatus;
import com.resortmanagement.system.billing.repository.FolioRepository;
import com.resortmanagement.system.booking.entity.BookingGuest;
import com.resortmanagement.system.booking.entity.Reservation;
import com.resortmanagement.system.booking.repository.ReservationRepository;
import com.resortmanagement.system.common.exception.ApplicationException;
import com.resortmanagement.system.booking.repository.BookingGuestRepository;
/**
 * FolioService
 * Purpose:
 *  - Service layer for Folio entity operations
 *  - Handles folio creation, updates, and state transitions (OPEN -> CLOSED -> VOID)
 * Business Logic:
 *  - closeFolio: Transitions folio from OPEN to CLOSED status
 *  - voidFolio: Transitions folio to VOID status (for incorrect/cancelled folios)
 *  - Validates folio state before operations
 *  - Financial records are never deleted, only state-transitioned
 */
@Service
@Transactional
public class FolioService {

    private final FolioRepository repository;
    private final ReservationRepository reservationRepository;
    private final BookingGuestRepository bookingGuestRepository;

    public FolioService(
        FolioRepository repository,
        ReservationRepository reservationRepository,
        BookingGuestRepository bookingGuestRepository
    ) {
        this.repository = repository;
        this.reservationRepository = reservationRepository;
        this.bookingGuestRepository = bookingGuestRepository;
    }

    @Transactional(readOnly = true)
    public List<Folio> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Folio> findById(UUID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Folio> findAllByReservationId(UUID reservationId) {
        return repository.findAllByReservationId(reservationId);
    }

    @Transactional(readOnly = true)
    public List<Folio> findByStatus(FolioStatus status) {
        return repository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public Folio findByReservationId(UUID reservationId) {
        return repository.findByReservationId(reservationId);
    }

    @Transactional(readOnly = true)
    public Reservation getReservation(UUID reservationId) {
        return reservationRepository.findByIdAndDeletedFalse(reservationId)
                .orElseThrow(() -> new ApplicationException("Reservation not found with id: " + reservationId));
    }

    @Transactional(readOnly = true)
    public BookingGuest getBookingGuest(UUID bookingGuestId) {
        return bookingGuestRepository.findByIdAndDeletedFalse(bookingGuestId)
                .orElseThrow(() -> new ApplicationException("Booking guest not found with id: " + bookingGuestId));
    }

    public Folio save(Folio folio) {
        // Validation: ensure folio has a name
        if (folio.getName() == null || folio.getName().trim().isEmpty()) {
            throw new ApplicationException("Folio name cannot be empty");
        }
        return repository.save(folio);
    }

    public Folio closeFolio(UUID folioId) {
        Folio folio = repository.findById(folioId)
                .orElseThrow(() -> new ApplicationException("Folio not found with id: " + folioId));
        
        if (folio.getStatus() == FolioStatus.CLOSED) {
            throw new ApplicationException("Folio is already closed");
        }
        if (folio.getStatus() == FolioStatus.VOID) {
            throw new ApplicationException("Cannot close a voided folio");
        }
        
        folio.setStatus(FolioStatus.CLOSED);
        return repository.save(folio);
    }

    public Folio voidFolio(UUID folioId) {
        Folio folio = repository.findById(folioId)
                .orElseThrow(() -> new ApplicationException("Folio not found with id: " + folioId));
        
        if (folio.getStatus() == FolioStatus.VOID) {
            throw new ApplicationException("Folio is already voided");
        }
        
        folio.setStatus(FolioStatus.VOID);
        return repository.save(folio);
    }
}
