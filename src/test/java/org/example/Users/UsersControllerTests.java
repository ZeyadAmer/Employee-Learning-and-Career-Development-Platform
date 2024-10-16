package org.example.Users;

import org.example.Controllers.UserController;
import org.example.Entities.User;
import org.example.Mappers.UserDTO;
import org.example.Services.UserService;
import org.example.Configurations.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UsersControllerTests {

    @Mock
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        String managerEmail = "manager@example.com";

        doNothing().when(userService).createUser(any(UserDTO.class), eq(managerEmail));

        ResponseEntity<String> response = userController.createUser(userDTO, managerEmail);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"token\":\"" + userDTO + "\"}", response.getBody());
        verify(userService, times(1)).createUser(userDTO, managerEmail);
    }

    @Test
    public void testDeleteUser() {
        String email = "user@example.com";

        ResponseEntity<String> response = userController.deleteUser(email);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("User Deleted", response.getBody());
        verify(userService, times(1)).deleteUser(email);
    }

    @Test
    public void testGetAllUsers() {
        UserDTO userDTO = new UserDTO();
        List<UserDTO> users = Arrays.asList(userDTO);
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUser() {
        int userId = 1;
        UserDTO userDTO = new UserDTO();
        when(request.getHeader("Authorization")).thenReturn("Bearer token");
        when(jwtUtil.extractUserId("token")).thenReturn(userId);
        when(userService.getUser(userId)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUser(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUser(userId);
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        UserDTO userDTO = new UserDTO();
        when(userService.getUser(userId)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUser(userId);
    }

    @Test
    public void testUpdateUserPassword() {
        String email = "user@example.com";
        String newPassword = "newPassword";

        ResponseEntity<String> response = userController.updateUserPassword(email, newPassword);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("password updated successfully", response.getBody());
        verify(userService, times(1)).updateUserPassword(email, newPassword);
    }

    @Test
    public void testUpdateUserTitle() {
        String email = "user@example.com";
        String title = "New Title";

        ResponseEntity<String> response = userController.updateUserTitle(title, email);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("title updated successfully", response.getBody());
        verify(userService, times(1)).updateUserTitle(email, title);
    }

    @Test
    public void testUpdateUserManager() {
        String email = "user@example.com";
        String managerEmail = "manager@example.com";

        ResponseEntity<String> response = userController.updateUserManager(managerEmail, email);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("manager updated successfully", response.getBody());
        verify(userService, times(1)).updateUserManager(email, managerEmail);
    }
}
