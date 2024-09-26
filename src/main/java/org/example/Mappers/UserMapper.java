package org.example.Mappers;

import org.example.Entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    List<UserDTO> usersToUserDTOs(List<User> users);
    List<User> userDTOsToUsers(List<UserDTO> userDTOs);

}
