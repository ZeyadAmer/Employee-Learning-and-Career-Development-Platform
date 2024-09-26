package org.example.Services;
import org.example.Entities.Title;
import org.example.Entities.User;
import org.example.Exceptions.ExistsException;
import org.example.Exceptions.NotManagerException;
import org.example.Exceptions.WrongPasswordException;
import org.example.Mappers.UserDTO;
import org.example.Mappers.UserMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Repositories.TitleRepository;
import org.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TitleRepository titleRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, TitleRepository titleRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.titleRepository = titleRepository;
        this.departmentRepository = departmentRepository;
    }
    public UserDTO getUser(String email, String password){
        if(userRepository.findByEmail(email).isEmpty()){
            throw new ExistsException("User does not exists");
        }
        User user = userRepository.findByEmail(email).get();
        if(!user.getPassword().equals(password)){
            throw new WrongPasswordException();
        }
        return userMapper.userToUserDTO(user);

    }
    public void createUser(UserDTO userDTO,String managerEmail) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new ExistsException("User already exists");
        }
        if(titleRepository.findByName(userDTO.getTitle().getName()).isEmpty()){
            throw new ExistsException("Title does not exists");
        }
        if(userDTO.getEmail().equals(managerEmail)){
            throw new ExistsException("User can't be managed by himself");
        }
        if(userRepository.findByEmail(managerEmail).isEmpty()){
            throw new ExistsException("Manager does not exist");
        }
        User manager = userRepository.findByEmail(managerEmail).get();
        if(!manager.getTitle().isManager()){
            throw new NotManagerException();
        }
        Title title = titleRepository.findByName(userDTO.getTitle().getName()).get();
        User user = userMapper.userDTOToUser(userDTO);
        user.setTitle(title);
        user.setDepartment(title.getDepartment());
        userRepository.save(user);
    }
    public void deleteUser(String email) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        User user = userRepository.findByEmail(email).get();
        userRepository.delete(user);
    }
    public void updateUserPassword(String email, String password) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        User user = userRepository.findByEmail(email).get();
        user.setPassword(password);
        userRepository.save(user);
    }
    public void updateUserTitle(String email, String title) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        if(titleRepository.findByName(title).isEmpty()){
            throw new ExistsException("Title does not exists");
        }
        User user = userRepository.findByEmail(email).get();
        Title updatedTitle = titleRepository.findByName(title).get();
        user.setTitle(updatedTitle);
        user.setDepartment(updatedTitle.getDepartment());
        userRepository.save(user);
    }
    public void updateUserManager(String email, String managerEmail) {
        if(userRepository.findByEmail(email).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        if(userRepository.findByEmail(managerEmail).isEmpty()){
            throw new ExistsException("Manager does not exist");
        }
        if(email.equals(managerEmail)){
            throw new ExistsException("User can't be managed by himself");
        }
        User manager = userRepository.findByEmail(managerEmail).get();
        if(!manager.getTitle().isManager()){
            throw new NotManagerException();
        }
        User user = userRepository.findByEmail(email).get();
        user.setManager(manager);
        userRepository.save(user);
    }
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDTOs(users);
    }
}
