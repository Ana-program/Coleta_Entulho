package br.com.osasco.wastepickup.mapper;

import br.com.osasco.wastepickup.dto.UserDTO;
import br.com.osasco.wastepickup.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public void updateEntityFromDto(UserDTO dto, User user) {
            Optional.ofNullable(dto.getName()).ifPresent(user::setName);
            Optional.ofNullable(dto.getAddress()).ifPresent(user::setAddress);
            Optional.ofNullable(dto.getPhone()).ifPresent(user::setPhone);
            Optional.ofNullable(dto.getEmail()).ifPresent(user::setEmail);
    }

    public UserDTO toDto(User entity) {
            UserDTO dto = new UserDTO();
            dto.setName(entity.getName());
            dto.setAddress(entity.getAddress());
            dto.setPhone(entity.getPhone());
            dto.setEmail(entity.getEmail());
            return dto;
        }
}
