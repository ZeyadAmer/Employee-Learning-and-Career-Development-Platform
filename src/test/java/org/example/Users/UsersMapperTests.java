package org.example.Users;

import org.example.Entities.User;
import org.example.Mappers.UserDTO;
import org.example.Mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class UsersMapperTests {
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    public void testUserToUserDTO() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void testUserDTOToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Jane");
        userDTO.setLastName("Doe");
        userDTO.setEmail("jane.doe@example.com");

        User user = userMapper.userDTOToUser(userDTO);

        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }

    @Test
    public void testUsersListToUserDTOsList() {
        User user1 = new User();
        user1.setFirstName("Alice");
        user1.setLastName("Smith");
        user1.setEmail("alice.smith@example.com");

        User user2 = new User();
        user2.setFirstName("Bob");
        user2.setLastName("Johnson");
        user2.setEmail("bob.johnson@example.com");

        List<User> users = Arrays.asList(user1, user2);
        List<UserDTO> userDTOs = userMapper.usersListToUserDTOsList(users);

        assertEquals(2, userDTOs.size());
        assertEquals(user1.getFirstName(), userDTOs.get(0).getFirstName());
        assertEquals(user2.getFirstName(), userDTOs.get(1).getFirstName());
    }

    @Test
    public void testUserDTOsListToUsersList() {
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setFirstName("Charlie");
        userDTO1.setLastName("Brown");
        userDTO1.setEmail("charlie.brown@example.com");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setFirstName("Diana");
        userDTO2.setLastName("Prince");
        userDTO2.setEmail("diana.prince@example.com");

        List<UserDTO> userDTOs = Arrays.asList(userDTO1, userDTO2);
        List<User> users = userMapper.userDTOsListToUsersList(userDTOs);

        assertEquals(2, users.size());
        assertEquals(userDTO1.getFirstName(), users.get(0).getFirstName());
        assertEquals(userDTO2.getFirstName(), users.get(1).getFirstName());
    }
}
