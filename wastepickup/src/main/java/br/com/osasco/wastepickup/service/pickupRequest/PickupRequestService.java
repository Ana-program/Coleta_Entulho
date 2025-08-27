package br.com.osasco.wastepickup.service.pickupRequest;

import br.com.osasco.wastepickup.entity.PickupRequest;
import br.com.osasco.wastepickup.exception.BusinessException;
import br.com.osasco.wastepickup.model.RequestStatus;
import br.com.osasco.wastepickup.repository.PickupRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupRequestService {

    @Autowired
    PickupRequestRepository repository;

    public PickupRequest createRequest(PickupRequest pickupRequest){

        pickupRequest.setStatus(RequestStatus.PENDING);

        boolean exists = repository.existsByAddress(pickupRequest.getAddress());
        if (exists) {
            throw new BusinessException("Já existe uma solicitação nesse endereço.");
        }
        if (pickupRequest.getEstimatedQuantity() <= 0) {
            throw new BusinessException("A quantidade estimada deve ser maior que zero.");
        }

        return repository.save(pickupRequest);
    }

    public List<PickupRequest> getAllRequest() {
        return repository.findAll();
    }

    public void updateStatus(Long id, RequestStatus status) {
        PickupRequest request = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada"));
        request.setStatus(status);

        repository.save(request);
    }

    public void cancelRequest(Long id) {
        repository.deleteById(id);
    }

}
