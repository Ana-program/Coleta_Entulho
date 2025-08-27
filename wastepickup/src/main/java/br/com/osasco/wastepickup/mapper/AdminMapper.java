package br.com.osasco.wastepickup.mapper;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.entity.Admin;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminMapper {

    public void updateEntityFromDto(AdminDTO dto, Admin admin) {
        Optional.ofNullable(dto.getName()).ifPresent(admin::setName);
        Optional.ofNullable(dto.getAddress()).ifPresent(admin::setAddress);
        Optional.ofNullable(dto.getPhone()).ifPresent(admin::setPhone);
        Optional.ofNullable(dto.getEmail()).ifPresent(admin::setEmail);
    }

    public AdminDTO toDto(Admin entity) {
        AdminDTO dto = new AdminDTO();
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
