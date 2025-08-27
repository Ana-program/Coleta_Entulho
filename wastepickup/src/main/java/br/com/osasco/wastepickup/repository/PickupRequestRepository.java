package br.com.osasco.wastepickup.repository;

import br.com.osasco.wastepickup.entity.PickupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupRequestRepository extends JpaRepository<PickupRequest, Long> {

    boolean existsByAddress(String address);
}
