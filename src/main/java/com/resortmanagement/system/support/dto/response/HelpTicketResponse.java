package com.resortmanagement.system.support.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.resortmanagement.system.support.enums.TicketPriority;
import com.resortmanagement.system.support.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelpTicketResponse {

    private UUID id;
    private String ticketNumber;

    private String category;
    private String description;

    private TicketPriority priority;
    private TicketStatus status;

    private UUID guestId;
    private UUID reservationId;
    
    private UUID assignedTo;
    
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
