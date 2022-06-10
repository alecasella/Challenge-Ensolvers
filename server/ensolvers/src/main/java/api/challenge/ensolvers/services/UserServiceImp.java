package api.challenge.ensolvers.services;

import api.challenge.ensolvers.dto.UserDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.InvalidAuthException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.models.User;
import api.challenge.ensolvers.repositories.UserRepository;
import api.challenge.ensolvers.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp  implements IUserService {

    @Autowired
    ModelMapper theModelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userDTO) throws AlreadyExistsException {

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new AlreadyExistsException("The user already exists");
        }

        User user = theModelMapper.map(userDTO, User.class);

        User userAdded = userRepository.save(user);

        return theModelMapper.map(userAdded, UserDTO.class);
    }

    @Override
    public Optional<User> login(UserDTO userDTO) throws ResourceNotFoundException, InvalidAuthException {

        Optional<User> findUser = userRepository.findByEmail(userDTO.getEmail());

        if(findUser.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }

        if(!(findUser.get().getPassword().equals(userDTO.getPassword()))){
            throw new InvalidAuthException("Password incorrect.");

        }

        return findUser;
    }

}
