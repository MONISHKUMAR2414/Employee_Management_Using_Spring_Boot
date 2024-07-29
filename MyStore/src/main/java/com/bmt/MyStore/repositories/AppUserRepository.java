package com.bmt.MyStore.repositories;

import com.bmt.MyStore.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
    List<AppUser> findByRole(String role);
}
