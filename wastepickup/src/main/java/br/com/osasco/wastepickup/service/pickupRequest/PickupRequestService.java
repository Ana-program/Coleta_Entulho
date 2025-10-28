package br.com.osasco.wastepickup.service.pickupRequest;

import br.com.osasco.wastepickup.dto.PickupRequestDTO;
import br.com.osasco.wastepickup.entity.PickupRequest;
import br.com.osasco.wastepickup.entity.User;
import br.com.osasco.wastepickup.exception.BusinessException;
import br.com.osasco.wastepickup.mapper.PickupRequestMapper;
import br.com.osasco.wastepickup.model.RequestStatus;
import br.com.osasco.wastepickup.repository.PickupRequestRepository;
import br.com.osasco.wastepickup.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickupRequestService {

    @Autowired
    PickupRequestRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PickupRequestMapper requestMapper;

    public void createRequest(Long userId, PickupRequestDTO pickupRequest){

        pickupRequest.setStatus(RequestStatus.PENDING);

        boolean exists = repository.existsByAddress(pickupRequest.getAddress());
        if (exists) {
            throw new BusinessException("There is already a request at this address");
        }
        if (pickupRequest.getEstimatedQuantity() <= 0) {
            throw new BusinessException("The estimated quantity must be greater than zero");
        }

        PickupRequest entity = requestMapper.toEntity(pickupRequest);
        User user = userRepository.findById(userId);
        entity.setUser(user);
       repository.save(entity);
    }

    public List<PickupRequest> getAllRequest(Long userId) {
        userRepository.findById(userId);
        return repository.findByUserId(userId);
    }

    public void updateStatus(Long id, RequestStatus status) {
        PickupRequest request = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));

        request.setStatus(status);
        repository.save(request);

        User user = request.getUser();
        userRepository.save(user);
    }

    public void cancelRequest(Long id) {
        repository.deleteById(id);
    }

}

