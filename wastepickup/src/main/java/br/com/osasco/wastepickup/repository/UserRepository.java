package br.com.osasco.wastepickup.repository;

import br.com.osasco.wastepickup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(String login);

    User findById(Long id);

    User findUserById(Long id);

}