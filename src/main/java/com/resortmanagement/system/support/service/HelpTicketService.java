package com.resortmanagement.system.support.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resortmanagement.system.common.guest.Guest;
import com.resortmanagement.system.common.guest.GuestRepository;
import com.resortmanagement.system.support.dto.request.HelpTicketCreateRequest;
import com.resortmanagement.system.support.dto.request.HelpTicketUpdateRequest;
import com.resortmanagement.system.support.dto.response.HelpTicketResponse;
import com.resortmanagement.system.support.entity.HelpTicket;
import com.resortmanagement.system.support.enums.TicketStatus;
import com.resortmanagement.system.support.mapper.HelpTicketMapper;
import com.resortmanagement.system.support.repository.HelpTicketRepository;

@Service
public class HelpTicketService {

    private final HelpTicketRepository repository;

    private final HelpTicketMapper mapper;

    private final GuestRepository guestRepository;

    public HelpTicketService(HelpTicketRepository repository,
                         HelpTicketMapper mapper,
                         GuestRepository guestRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.guestRepository = guestRepository;
    }

    // public HelpTicketService(HelpTicketMapper mapper, HelpTicketRepository repository) {
    //     this.mapper = mapper;
    //     this.repository = repository;
    //     this.guestRepository = null;
    // }

    public HelpTicketResponse create(HelpTicketCreateRequest request) {

        HelpTicket entity = new HelpTicket();

        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setPriority(request.getPriority());
        entity.setStatus(TicketStatus.OPEN);

        // ✅ ticket number generation
        entity.setTicketNumber("TKT-" + System.currentTimeMillis());

        return mapper.toResponse(repository.save(entity));
    }

    public List<HelpTicketResponse> getAll() {
        return repository.findByDeletedFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public HelpTicketResponse update(UUID id, HelpTicketUpdateRequest request) {

        HelpTicket entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (request.getPriority() != null)
            entity.setPriority(request.getPriority());

        if (request.getStatus() != null)
            entity.setStatus(request.getStatus());

        if (request.getAssignedTo() != null) {
            Guest guest = guestRepository.findByIdAndDeletedFalse(request.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Guest not found"));
            entity.setAssignedTo(guest);
        }

        return mapper.toResponse(repository.save(entity));
    }

    public void delete(UUID id) {

        HelpTicket entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        entity.setDeleted(true);
        repository.save(entity);
    }
}
