package com.resortmanagement.system.support.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.resortmanagement.system.support.enums.CommunicationStatus;
import com.resortmanagement.system.support.enums.CommunicationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunicationResponse {

    private UUID id;

    private UUID guestId;

    private UUID reservationId;

    private CommunicationType type;

    private String to;

    private String subject;

    private String bodySnippet;

    private CommunicationStatus status;

    private LocalDateTime sentAt;
    private String channel;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
    private boolean deleted;
    private Instant deletedAt;
}
