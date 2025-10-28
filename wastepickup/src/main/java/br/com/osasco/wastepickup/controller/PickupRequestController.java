package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.dto.PickupRequestDTO;
import br.com.osasco.wastepickup.entity.PickupRequest;
import br.com.osasco.wastepickup.model.RequestStatus;
import br.com.osasco.wastepickup.service.pickupRequest.PickupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class PickupRequestController {

    @Autowired
    private PickupRequestService service;

    @GetMapping("/{userId}")
    public ResponseEntity<List<PickupRequest>> getAllRequests(@PathVariable Long userId ){
        List<PickupRequest> requests =  service.getAllRequest(userId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> createPickup(@PathVariable Long userId, @RequestBody PickupRequestDTO request) {
        service.createRequest(userId, request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateStatus(@RequestParam Long id, @RequestParam RequestStatus status){
        service.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelPickup(@PathVariable Long id) {
        service.cancelRequest(id);
        return ResponseEntity.noContent().build();
    }


}
