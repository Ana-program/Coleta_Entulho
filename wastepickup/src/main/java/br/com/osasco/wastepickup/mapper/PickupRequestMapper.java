package br.com.osasco.wastepickup.mapper;

import br.com.osasco.wastepickup.dto.PickupRequestDTO;
import br.com.osasco.wastepickup.entity.PickupRequest;
import org.springframework.stereotype.Component;

@Component
public class PickupRequestMapper {

    public PickupRequest toEntity(PickupRequestDTO dto) {
        PickupRequest entity = new PickupRequest();
        entity.setRequesterName(dto.getRequesterName());
        entity.setDebrisType(dto.getDebrisType());
        entity.setEstimatedQuantity(dto.getEstimatedQuantity());
        entity.setRequestDate(dto.getRequestDate());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public PickupRequestDTO toDto(PickupRequest entity) {
        PickupRequestDTO dto = new PickupRequestDTO();
        dto.setAddress(entity.getAddress());
        dto.setDebrisType(entity.getDebrisType());
        dto.setEstimatedQuantity(entity.getEstimatedQuantity());
        dto.setRequestDate(entity.getRequestDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

}



