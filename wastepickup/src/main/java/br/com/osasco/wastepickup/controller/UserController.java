package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.UserDTO;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public UserDTO getUser(@PathVariable Long id){
       return service.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        UserDTO updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user){
        service.createUser(user);
        return ResponseEntity.noContent().build();
    }
}
