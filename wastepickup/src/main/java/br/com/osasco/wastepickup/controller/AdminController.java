package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/requests")
public class AdminController {

    @Autowired
    AdminService service;

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO dto) {
        AdminDTO updatedAdmin = service.updateAdmin(id, dto);
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminDTO getAdmin(@PathVariable Long id){
        return service.getAdmin(id);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminDTO>> findAllAdmin() {
        List<AdminDTO> admins = service.getAllAdmin();
        return ResponseEntity.ok(admins);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAdmin(@PathVariable Long id){
         service.deleteAdmin(id);
    }

}
