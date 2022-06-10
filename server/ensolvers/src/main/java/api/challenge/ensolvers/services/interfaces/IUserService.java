package api.challenge.ensolvers.services.interfaces;

import api.challenge.ensolvers.dto.UserDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.InvalidAuthException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.models.User;

import java.util.Optional;

public interface IUserService {
    UserDTO save(UserDTO user) throws AlreadyExistsException;
    Optional<User> login(UserDTO user) throws ResourceNotFoundException, InvalidAuthException;
}