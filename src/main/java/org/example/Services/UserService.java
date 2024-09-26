package org.example.Services;

import org.example.Entities.Title;
import org.example.Entities.User;
import org.example.Exceptions.ExistsException;
import org.example.Exceptions.NotManagerException;
import org.example.Mappers.UserDTO;
import org.example.Mappers.UserMapper;
import org.example.Repositories.DepartmentRepository;
import org.example.Repositories.TitleRepository;
import org.example.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new ExistsException("User already exists");
        }
        if(titleRepository.findByName(userDTO.getTitle()).isEmpty()){
            throw new ExistsException("Title does not exists");
        }
        if(departmentRepository.findByName(userDTO.getDepartment()).isEmpty()){
            throw new ExistsException("This department does not exist");
        }
        if(userDTO.getEmail().equals(userDTO.getManager())){
            throw new ExistsException("User can't be managed by himself");
        }
        if(userRepository.findByEmail(userDTO.getManager()).isEmpty()){
            throw new ExistsException("Manager does not exist");
        }
        User manager = userRepository.findByEmail(userDTO.getManager()).get();
        if(!manager.getTitle().isManager()){
            throw new NotManagerException();
        }
        User user = userMapper.userDTOToUser(userDTO);
        userRepository.save(user);
    }
    public void deleteUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        User user = userMapper.userDTOToUser(userDTO);
        userRepository.delete(user);
    }
    public void updateUserPassword(UserDTO userDTO, String password) {
        if(userRepository.findByEmail(userDTO.getEmail()).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        User user = userMapper.userDTOToUser(userDTO);
        user.setPassword(password);
        userRepository.save(user);
    }
    public void updateUserTitle(UserDTO userDTO, String title) {
        if(userRepository.findByEmail(userDTO.getEmail()).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        if(titleRepository.findByName(title).isEmpty()){
            throw new ExistsException("Title does not exists");
        }
        User user = userMapper.userDTOToUser(userDTO);
        Title updatedTitle = titleRepository.findByName(title).get();
        user.setTitle(updatedTitle);
        user.setDepartment(updatedTitle.getDepartment());
        userRepository.save(user);
    }
    public void updateUserManager(UserDTO userDTO, String managerEmail) {
        if(userRepository.findByEmail(userDTO.getEmail()).isEmpty()){
            throw new ExistsException("User does not exist");
        }
        if(userRepository.findByEmail(managerEmail).isEmpty()){
            throw new ExistsException("Manager does not exist");
        }
        if(userDTO.getEmail().equals(managerEmail)){
            throw new ExistsException("User can't be managed by himself");
        }
        User manager = userRepository.findByEmail(managerEmail).get();
        if(!manager.getTitle().isManager()){
            throw new NotManagerException();
        }
        User user = userMapper.userDTOToUser(userDTO);
        user.setManager(manager);
        userRepository.save(user);
    }
}
