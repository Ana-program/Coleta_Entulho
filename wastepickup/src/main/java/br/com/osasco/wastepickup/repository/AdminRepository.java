package br.com.osasco.wastepickup.repository;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, String>{

    UserDetails findByLogin(String login);

    AdminDTO findById(Long id);

    Admin findUserById(Long id);

}



