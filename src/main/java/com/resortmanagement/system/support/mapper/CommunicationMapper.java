package com.resortmanagement.system.support.mapper;

import com.resortmanagement.system.support.dto.response.CommunicationResponse;
import com.resortmanagement.system.support.entity.Communication;

public class CommunicationMapper {

    private CommunicationMapper() {}

    public static CommunicationResponse toResponse(Communication entity) {

        CommunicationResponse res = new CommunicationResponse();

        res.setId(entity.getId());
        res.setType(entity.getType());
        res.setTo(entity.getTo());
        res.setSubject(entity.getSubject());
        res.setBodySnippet(entity.getBodySnippet());
        res.setStatus(entity.getStatus());
        res.setSentAt(entity.getSentAt());
        res.setChannel(entity.getChannel());

        res.setGuestId(entity.getGuest() != null ? entity.getGuest().getId() : null);
        res.setReservationId(entity.getReservation() != null ? entity.getReservation().getId() : null);

        res.setCreatedBy(entity.getCreatedBy());
        res.setCreatedAt(entity.getCreatedAt());
        res.setUpdatedBy(entity.getUpdatedBy());
        res.setUpdatedAt(entity.getUpdatedAt());
        res.setDeleted(entity.isDeleted());
        res.setDeletedAt(entity.getDeletedAt());

        return res;
    }
}
