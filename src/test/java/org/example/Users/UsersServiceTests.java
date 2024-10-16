package org.example.Users;
import org.example.Entities.Department;
import org.example.Entities.Title;
import org.example.Entities.User;
import org.example.Exceptions.ExistsException;
import org.example.Exceptions.NotManagerException;
import org.example.Exceptions.WrongPasswordException;
import org.example.Mappers.DepartmentDTO;
import org.example.Mappers.TitleDTO;
import org.example.Mappers.UserDTO;
import org.example.Mappers.UserMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Repositories.TitleRepository;
import org.example.Repositories.UserRepository;
import org.example.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class UsersServiceTests {
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    TitleRepository titleRepository;
    @Mock
    DepartmentRepository departmentRepository;
    @InjectMocks
    UserService userService;

    @Test
    public void GetUser_Success(){
        User user = new User();
        UserDTO userDto1 = new UserDTO();
        userDto1.setEmail("email@email.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(user)).thenReturn(userDto1);
        UserDTO userDTO = userService.getUser(1);
        assertEquals("email@email.com",userDTO.getEmail());
    }
    @Test
    public void GetUser_NotExist(){
        User user = new User();
        UserDTO userDto1 = new UserDTO();
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.getUser(1));
    }

    @Test
    public void DeleteUser_Success(){
        User user = new User();
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        userService.deleteUser("email@email.com");
    }
    @Test
    public void DeleteUser_NotExist(){
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.deleteUser("email@email.com"));
    }
    @Test
    public void UpdateUserPassword_Success(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        userService.updateUserPassword("email@email.com","passworddd");
    }
    @Test
    public void UpdateUserPassword_NotExist(){
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserPassword("email@email.com","passworddd"));
    }
    @Test
    public void GetAllUsers_Success(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setEmail("email@email.com");
        User user1 = new User();
        user1.setEmail("email1@email.com");
        users.add(user);
        users.add(user1);
        UserDTO userDto1 = new UserDTO();
        userDto1.setEmail("email1@email.com");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email1@email.com");
        List<UserDTO> userDTOs = new ArrayList<>();
        userDTOs.add(userDTO);
        userDTOs.add(userDto1);
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.usersListToUserDTOsList(users)).thenReturn(userDTOs);
        List<UserDTO> usersOut = userService.getAllUsers();
        assertEquals("email1@email.com",usersOut.get(0).getEmail());
        assertEquals("email1@email.com",usersOut.get(1).getEmail());
    }
    @Test
    public void UpdateUserManager_Success(){
        User user = new User();
        user.setEmail("email@email.com");
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",true));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        userService.updateUserManager("email@email.com","manager@email.com");
    }
    @Test
    public void UpdateUserManager_UserNotExist(){
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserManager_ManagerNotExist(){
        User user = new User();
        user.setEmail("email@email.com");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserManager_ManagerIsUser(){
        User user = new User();
        user.setEmail("email@email.com");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","email@email.com"));
    }
    @Test
    public void UpdateUserManager_NotManager(){
        User user = new User();
        user.setEmail("email@email.com");
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",false));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        assertThrows(NotManagerException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserTitle_Success(){
        User user = new User();
        user.setEmail("email@email.com");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Title title = new Title(new Department(),"title",true);
        when(titleRepository.findByName("title")).thenReturn(Optional.of(title));
        userService.updateUserTitle("email@email.com","title");
    }
    @Test
    public void UpdateUserTitle_UserNotExist(){
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserTitle("email@email.com","title"));
    }
    @Test
    public void UpdateUserTitle_TitleNotExist(){
        User user = new User();
        user.setEmail("email@email.com");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(titleRepository.findByName("title")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserTitle("email@email.com","title"));
    }
    @Test
    public void CreateUser_Success(){
        User user = new User();
        Title title = new Title(new Department(),"title0",false);
        user.setEmail("email@email.com");
        user.setTitle(title);
        when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",true));
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        userService.createUser(userDTO, manager.getEmail());
    }
    @Test
    public void CreateUser_UserExists(){
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        assertThrows(ExistsException.class,()-> userService.createUser(userDTO, "manager@email.com"));
    }
    @Test
    public void CreateUser_TitleNotExist() {
        when(titleRepository.findByName("title0")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        assertThrows(ExistsException.class, () -> userService.createUser(userDTO, "manager@email.com"));
    }

    @Test
    public void CreateUser_ManagerNotExist() {
        Title title = new Title(new Department(), "title0", false);
        when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        assertThrows(ExistsException.class, () -> userService.createUser(userDTO, "manager@email.com"));
    }

    @Test
    public void CreateUser_NotManager() {
        User user = new User();
        Title title = new Title(new Department(), "title0", false); // Title is not a manager
        user.setEmail("email@email.com");
        user.setTitle(title);
        when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        User manager = new User();
        manager.setEmail("manager@email.com");
        Title managerTitle = new Title(new Department(), "title", false); // Not a manager
        manager.setTitle(managerTitle);
        when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        assertThrows(NotManagerException.class, () -> userService.createUser(userDTO, "manager@email.com"));
    }


}
