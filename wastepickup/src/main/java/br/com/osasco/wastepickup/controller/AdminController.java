package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.entity.Admin;
import br.com.osasco.wastepickup.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/requests")
public class AdminController {

    @Autowired
    AdminService service;

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO dto) {
        AdminDTO updatedUser = service.updateAdmin(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<Void> createAdmin(@RequestBody Admin admin){
        service.createAdmin(admin);
        return ResponseEntity.noContent().build();
    }
}
