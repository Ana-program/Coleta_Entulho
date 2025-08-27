package br.com.osasco.wastepickup.service.user;

import br.com.osasco.wastepickup.dto.UserDTO;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.mapper.UserMapper;
import br.com.osasco.wastepickup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper userMapper;

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = repository.findUserById(id);
        userMapper.updateEntityFromDto(dto, user);
        repository.save(user);

        return userMapper.toDto(user);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public UserDTO getUser(Long id) {
        return repository.findById(id);
    }
}
