package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.AuthenticationDTO;
import br.com.osasco.wastepickup.dto.LoginResponseDTO;
import br.com.osasco.wastepickup.dto.RegisterDTO;
import br.com.osasco.wastepickup.entity.Admin;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.exception.BusinessException;
import br.com.osasco.wastepickup.infra.security.TokenService;
import br.com.osasco.wastepickup.mapper.AdminMapper;
import br.com.osasco.wastepickup.mapper.UserMapper;
import br.com.osasco.wastepickup.repository.AdminRepository;
import br.com.osasco.wastepickup.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByLogin(data.login()) != null
                || adminRepository.findByLogin(data.login()) != null) {
            throw new BusinessException("Login already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        User newUser = new User();
        userMapper.builderUser(newUser, data, encryptedPassword);
        repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByLogin(data.login()) != null
                || adminRepository.findByLogin(data.login()) != null) {
            throw new BusinessException("Login already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.password());

        Admin newAdmin = new Admin();
        adminMapper.builderAdmin(newAdmin, data, encryptedPassword);
        adminRepository.save(newAdmin);

        return ResponseEntity.ok().build();
    }



}
