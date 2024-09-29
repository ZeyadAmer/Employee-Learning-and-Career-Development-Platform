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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void GetUser_Succes(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        UserDTO userDto1 = new UserDTO();
        userDto1.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userMapper.userToUserDTO(user)).thenReturn(userDto1);
        UserDTO userDTO = userService.getUser("email@email.com","password");
        assertEquals("email@email.com",userDTO.getEmail());
    }
    @Test
    public void GetUser_NotExist(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        UserDTO userDto1 = new UserDTO();
        userDto1.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        Mockito.when(userMapper.userToUserDTO(user)).thenReturn(userDto1);
        assertThrows(ExistsException.class,()->userService.getUser("email@email.com","password"));
    }
    @Test
    public void GetUser_WrongPassword(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        UserDTO userDto1 = new UserDTO();
        userDto1.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userMapper.userToUserDTO(user)).thenReturn(userDto1);
        assertThrows(WrongPasswordException.class,()->userService.getUser("email@email.com","passwd"));
    }
    @Test
    public void DeleteUser_Succes(){
        User user = new User();
        user.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        userService.deleteUser("email@email.com");
    }
    @Test
    public void DeleteUser_NotExist(){
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.deleteUser("email@email.com"));
    }
    @Test
    public void UpdateUserPassword_Success(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        userService.updateUserPassword("email@email.com","passworddd");
    }
    @Test
    public void UpdateUserPassword_NotExist(){
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
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
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userMapper.usersListToUserDTOsList(users)).thenReturn(userDTOs);
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
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        userService.updateUserManager("email@email.com","manager@email.com");
    }
    @Test
    public void UpdateUserManager_UserNotExist(){
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserManager_ManagerNotExist(){
        User user = new User();
        user.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserManager_ManagerIsUser(){
        User user = new User();
        user.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        assertThrows(ExistsException.class,()->userService.updateUserManager("email@email.com","email@email.com"));
    }
    @Test
    public void UpdateUserManager_NotManager(){
        User user = new User();
        user.setEmail("email@email.com");
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",false));
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        assertThrows(NotManagerException.class,()->userService.updateUserManager("email@email.com","manager@email.com"));
    }
    @Test
    public void UpdateUserTitle_Success(){
        User user = new User();
        user.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Title title = new Title(new Department(),"title",true);
        Mockito.when(titleRepository.findByName("title")).thenReturn(Optional.of(title));
        userService.updateUserTitle("email@email.com","title");
    }
    @Test
    public void UpdateUserTitle_UserNotExist(){
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserTitle("email@email.com","title"));
    }
    @Test
    public void UpdateUserTitle_TitleNotExist(){
        User user = new User();
        user.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        Mockito.when(titleRepository.findByName("title")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()->userService.updateUserTitle("email@email.com","title"));
    }
    @Test
    public void CreateUser_Success(){
        User user = new User();
        Title title = new Title(new Department(),"title0",false);
        user.setEmail("email@email.com");
        user.setTitle(title);
        Mockito.when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        Mockito.when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",true));
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        userService.createUser(userDTO, manager.getEmail());
    }
    @Test
    public void CreateUser_UserExists(){
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(user));
        assertThrows(ExistsException.class,()-> userService.createUser(userDTO, "manager@email.com"));
    }
    @Test
    public void CreateUser_TitleNotExist(){
        User user = new User();
        Mockito.when(titleRepository.findByName("title0")).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        Mockito.when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        assertThrows(ExistsException.class,()-> userService.createUser(userDTO,"manager@email.com"));
    }
    @Test
    public void CreateUser_ManagerNotExist(){
        User user = new User();
        Title title = new Title(new Department(),"title0",false);
        user.setEmail("email@email.com");
        user.setTitle(title);
        Mockito.when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        Mockito.when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.empty());
        assertThrows(ExistsException.class,()-> userService.createUser(userDTO,"manager@email.com"));
    }
    @Test
    public void CreateUser_NotManager(){
        User user = new User();
        Title title = new Title(new Department(),"title0",false);
        user.setEmail("email@email.com");
        user.setTitle(title);
        Mockito.when(titleRepository.findByName("title0")).thenReturn(Optional.of(title));
        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.empty());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setName("title0");
        userDTO.setTitle(titleDTO);
        Mockito.when(userMapper.userDTOToUser(userDTO)).thenReturn(user);
        User manager = new User();
        manager.setEmail("manager@email.com");
        manager.setTitle(new Title(new Department(),"title",false));
        Mockito.when(userRepository.findByEmail("manager@email.com")).thenReturn(Optional.of(manager));
        assertThrows(NotManagerException.class,()-> userService.createUser(userDTO,"manager@email.com"));
    }
}
