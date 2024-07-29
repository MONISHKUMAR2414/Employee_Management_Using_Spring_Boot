package com.bmt.MyStore.services;

import com.bmt.MyStore.models.AdminUser;
import com.bmt.MyStore.repositories.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public AdminUser getAdminByEmail(String email) {
        Optional<AdminUser> adminOptional = adminUserRepository.findByEmail(email);
        return adminOptional.orElseThrow(() -> new UsernameNotFoundException("Admin user not found"));
    }
}
