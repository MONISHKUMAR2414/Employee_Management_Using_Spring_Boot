package com.bmt.MyStore;

import com.bmt.MyStore.models.AppUser;
import com.bmt.MyStore.repositories.AppUserRepository;
import com.bmt.MyStore.services.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByEmail_Success() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setEmail("mohan@gmail.com");
        mockUser.setFirstName("mohan");
        mockUser.setLastName("raj");
        mockUser.setAddress("rec college");

        when(appUserRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

        // Act
        AppUser user = appUserService.getUserByEmail("mohan@gmail.com");

        // Assert
        assertNotNull(user);
        assertEquals("mohan@gmail.com", user.getEmail());
        assertEquals("mohan", user.getFirstName());
        assertEquals("raj", user.getLastName());
        assertEquals("rec college", user.getAddress());
    }

    @Test
    public void testGetUsersByRole_Success() {
        // Arrange
        AppUser mockUser = new AppUser();
        mockUser.setEmail("client@example.com");
        mockUser.setRole("Client");

        when(appUserRepository.findByRole(anyString())).thenReturn(List.of(mockUser));

        // Act
        List<AppUser> clients = appUserService.getUsersByRole("Client");

        // Assert
        assertNotNull(clients);
        assertEquals(1, clients.size());
        assertEquals("client@example.com", clients.get(0).getEmail());
    }

    @Test
    public void testGetUserByEmail_UserNotFound() {
        // Arrange
        when(appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            appUserService.getUserByEmail("nonexistent@example.com");
        });
    }
}
