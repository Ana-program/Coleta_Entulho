package br.com.osasco.wastepickup.mapper;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.dto.RegisterDTO;
import br.com.osasco.wastepickup.entity.Admin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdminMapper {

    public void updateEntityFromDto(AdminDTO dto, Admin admin) {
        Optional.ofNullable(dto.getName()).ifPresent(admin::setName);
        Optional.ofNullable(dto.getEmail()).ifPresent(admin::setEmail);
        Optional.ofNullable(dto.getPhone()).ifPresent(admin::setPhone);
        Optional.ofNullable(dto.getGender()).ifPresent(admin::setGender);
        Optional.ofNullable(dto.getBirthDate()).ifPresent(admin::setBirthDate);
        Optional.ofNullable(dto.getState()).ifPresent(admin::setState);
        Optional.ofNullable(dto.getCity()).ifPresent(admin::setCity);
        Optional.ofNullable(dto.getAddress()).ifPresent(admin::setStreet);
        Optional.ofNullable(dto.getComplement()).ifPresent(admin::setComplement);
        Optional.ofNullable(dto.getNeighborhood()).ifPresent(admin::setNeighborhood);
        Optional.ofNullable(dto.getMobile()).ifPresent(admin::setMobile);
        Optional.ofNullable(dto.getNumber()).ifPresent(admin::setNumber);
        Optional.ofNullable(dto.getZipCode()).ifPresent(admin::setNumber);
    }

    public AdminDTO toDto(Admin entity) {
        AdminDTO dto = new AdminDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setBirthDate(entity.getBirthDate());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setAddress(entity.getStreet());
        dto.setComplement(entity.getComplement());
        dto.setNeighborhood(entity.getNeighborhood());
        dto.setMobile(entity.getMobile());
        dto.setNumber(entity.getNumber());
        dto.setZipCode(entity.getZipCode());
        return dto;
    }

    public List<AdminDTO> toDTOList(List<Admin> admin){
        return admin.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void builderAdmin(Admin entity, RegisterDTO data, String encryptedPassword) {
        entity.setLogin(data.login());
        entity.setPassword(encryptedPassword);
        entity.setName(data.name());
        entity.setCpf(data.cpf());
        entity.setRg(data.rg());
        entity.setBirthDate(data.birthDate());
        entity.setGender(data.gender());
        entity.setZipCode(data.zipCode());
        entity.setStreet(data.street());
        entity.setNumber(data.number());
        entity.setComplement(data.complement());
        entity.setNeighborhood(data.neighborhood());
        entity.setCity(data.city());
        entity.setState(data.state());
        entity.setPhone(data.phone());
        entity.setMobile(data.mobile());
        entity.setEmail(data.email());
    }
}
