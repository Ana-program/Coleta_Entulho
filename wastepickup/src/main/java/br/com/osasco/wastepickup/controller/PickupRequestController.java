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

    @GetMapping
    public ResponseEntity<List<PickupRequest>> getAllRequests(){
        List<PickupRequest> requests =  service.getAllRequest();
        return ResponseEntity.ok(requests);
    }

    @PostMapping
    public ResponseEntity<Void> createPickup(@RequestBody PickupRequestDTO request) {
        service.createRequest(request);
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
