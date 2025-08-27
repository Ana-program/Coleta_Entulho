package br.com.osasco.wastepickup.dto;

import br.com.osasco.wastepickup.role.Role;

public record RegisterDTO(String login, String password, Role role) {
}
