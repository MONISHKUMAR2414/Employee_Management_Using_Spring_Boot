package com.bmt.MyStore.services;

import com.bmt.MyStore.models.AppUser;
import com.bmt.MyStore.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(username);
        if (appUserOptional.isPresent()) {
            AppUser appUser = appUserOptional.get();
            return User.builder()
                    .username(appUser.getEmail())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole()) // Use the role defined in the entity
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
