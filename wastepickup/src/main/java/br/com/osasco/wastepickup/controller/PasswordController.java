package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.ChangePasswordDTO;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.repository.AdminRepository;
import br.com.osasco.wastepickup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO request) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserBycpf(request.getCpf()));

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("Usuário com CPF informado não encontrado.");
        }

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }
}
