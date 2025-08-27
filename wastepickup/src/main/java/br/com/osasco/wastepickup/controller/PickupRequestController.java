package br.com.osasco.wastepickup.controller;

import br.com.osasco.wastepickup.entity.PickupRequest;
import br.com.osasco.wastepickup.model.RequestStatus;
import br.com.osasco.wastepickup.service.pickupRequest.PickupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PickupRequest> createPickup(@RequestBody PickupRequest request) {
        PickupRequest createdRequest = service.createRequest(request);
        return ResponseEntity.ok(createdRequest);
    }

    @PutMapping("/updateStaus")
    public ResponseEntity<Void> updateStatusC(Long id, RequestStatus status){
        service.updateStatus(id,status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelPickup(@PathVariable Long id) {
        service.cancelRequest(id);
        return ResponseEntity.noContent().build();
    }

}
