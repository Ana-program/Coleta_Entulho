package br.com.osasco.wastepickup.mapper;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.dto.RegisterDTO;
import br.com.osasco.wastepickup.dto.UserDTO;
import br.com.osasco.wastepickup.entity.Admin;
import br.com.osasco.wastepickup.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public void updateEntityFromDto(UserDTO dto, User user) {
        Optional.ofNullable(dto.getName()).ifPresent(user::setName);
        Optional.ofNullable(dto.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(dto.getPhone()).ifPresent(user::setPhone);
        Optional.ofNullable(dto.getGender()).ifPresent(user::setGender);
        Optional.ofNullable(dto.getBirthDate()).ifPresent(user::setBirthDate);
        Optional.ofNullable(dto.getState()).ifPresent(user::setState);
        Optional.ofNullable(dto.getCity()).ifPresent(user::setCity);
        Optional.ofNullable(dto.getAddress()).ifPresent(user::setStreet);
        Optional.ofNullable(dto.getComplement()).ifPresent(user::setComplement);
        Optional.ofNullable(dto.getNeighborhood()).ifPresent(user::setNeighborhood);
        Optional.ofNullable(dto.getMobile()).ifPresent(user::setMobile);
        Optional.ofNullable(dto.getNumber()).ifPresent(user::setNumber);
        Optional.ofNullable(dto.getZipCode()).ifPresent(user::setNumber);
    }

    public UserDTO toDto(User entity) {
            UserDTO dto = new UserDTO();
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

    public List<UserDTO> toDTOList(List<User> users){
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void builderUser(User entity, RegisterDTO data, String encryptedPassword) {
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
