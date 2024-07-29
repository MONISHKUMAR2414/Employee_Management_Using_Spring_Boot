package com.bmt.MyStore;

import com.bmt.MyStore.models.AdminUser;
import com.bmt.MyStore.models.AppUser;
import com.bmt.MyStore.repositories.AdminUserRepository;
import com.bmt.MyStore.repositories.AppUserRepository;
import com.bmt.MyStore.services.AdminUserService;
import com.bmt.MyStore.services.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AdminUserServiceTest {

    @Mock
    private AdminUserRepository adminUserRepository;

    @InjectMocks
    private AdminUserService adminUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByEmail_Success() {
        // Arrange
        AdminUser mockUser = new AdminUser();
        mockUser.setEmail("mohan@gmail.com");
        mockUser.setFirstName("mohan");
        mockUser.setLastName("raj");
        mockUser.setAddress("rec college");

        when(adminUserRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        // Act
        AdminUser user = adminUserService.getAdminByEmail("mohan@gmail.com");

        // Assert
        assertNotNull(user);
        assertEquals("mohan@gmail.com", user.getEmail());
        assertEquals("mohan", user.getFirstName());
        assertEquals("raj", user.getLastName());
        assertEquals("rec college", user.getAddress());
    }
}