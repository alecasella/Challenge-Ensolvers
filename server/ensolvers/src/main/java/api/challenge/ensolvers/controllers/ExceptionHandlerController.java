package api.challenge.ensolvers.controllers;

import api.challenge.ensolvers.dto.ErrorsDetailDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.InvalidAuthException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<ErrorsDetailDTO> handleInvalidAuthenticationException(InvalidAuthException exception, WebRequest webRequest){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorsDetailDTO(new Date(), exception.getMessage(), webRequest.getDescription(false)));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorsDetailDTO> handleAlreadyExistsException(AlreadyExistsException exception, WebRequest webRequest){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorsDetailDTO(new Date(), exception.getMessage(), webRequest.getDescription(false)));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorsDetailDTO> handleNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorsDetailDTO(new Date(), exception.getMessage(), webRequest.getDescription(false)));
    }
}
