package br.com.osasco.wastepickup.service.user;

import br.com.osasco.wastepickup.dto.UserDTO;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.mapper.UserMapper;
import br.com.osasco.wastepickup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper userMapper;

    public UserDTO updateUser(String id, UserDTO dto) {
        User user = repository.findUserById(Long.valueOf(id));
        userMapper.updateEntityFromDto(dto, user);
        repository.save(user);

        return userMapper.toDto(user);
    }

    public UserDTO getUser(Long id) {
        User user = repository.findById(id);
        return userMapper.toDto(user);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = repository.findAll();
        return userMapper.toDTOList(users);
    }

    public void deleteUser(Long id) {
        repository.deleteById(String.valueOf(id));
    }
}

