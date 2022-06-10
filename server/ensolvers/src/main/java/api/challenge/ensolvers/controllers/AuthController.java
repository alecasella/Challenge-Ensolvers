package api.challenge.ensolvers.controllers;

import api.challenge.ensolvers.dto.UserDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.InvalidAuthException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.repositories.UserRepository;
import api.challenge.ensolvers.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) throws ResourceNotFoundException, InvalidAuthException {
        return ResponseEntity.ok().body(userService.login(userDTO));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserDTO userDTO) throws AlreadyExistsException {
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.CREATED);
    }
}
