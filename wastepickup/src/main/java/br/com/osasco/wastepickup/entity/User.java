package br.com.osasco.wastepickup.entity;

import br.com.osasco.wastepickup.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String login;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String name;
    private String address;
    private String phone;
    private Boolean active;

    @Column(length = 11)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 10)
    private String gender;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 15)
    private String mobile;

    public User (String login, String password, Role role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}








