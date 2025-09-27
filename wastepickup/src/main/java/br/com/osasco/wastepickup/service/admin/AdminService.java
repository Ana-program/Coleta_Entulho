package br.com.osasco.wastepickup.service.admin;

import br.com.osasco.wastepickup.dto.AdminDTO;
import br.com.osasco.wastepickup.entity.Admin;
import br.com.osasco.wastepickup.mapper.AdminMapper;
import br.com.osasco.wastepickup.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository repository;

    @Autowired
    AdminMapper adminMapper;

    public AdminDTO updateAdmin(Long id, AdminDTO dto) {
        Admin admin = repository.findUserById(id);
        adminMapper.updateEntityFromDto(dto, admin);
        repository.save(admin);

        return adminMapper.toDto(admin);
    }

    public AdminDTO getAdmin(Long id) {
        return repository.findById(id);
    }

    public List<AdminDTO> getAllAdmin() {
        List<Admin> admins = repository.findAll();
        return adminMapper.toDTOList(admins);
    }

    public void deleteAdmin(Long id) {
        repository.deleteById(String.valueOf(id));
    }
}
