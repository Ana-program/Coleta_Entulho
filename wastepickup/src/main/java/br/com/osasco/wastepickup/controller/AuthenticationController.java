package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.AuthenticationDTO;
import br.com.osasco.wastepickup.dto.RegisterDTO;
import br.com.osasco.wastepickup.entity.Admin;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.repository.AdminRepository;
import br.com.osasco.wastepickup.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/loginUser")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registerUser")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity registerAdmin(@RequestBody @Valid RegisterDTO data) {
        if (this.adminRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Admin newAdmin = new Admin(data.login(), encryptedPassword, data.role());
        this.adminRepository.save(newAdmin);

        return ResponseEntity.ok().build();
    }


}
