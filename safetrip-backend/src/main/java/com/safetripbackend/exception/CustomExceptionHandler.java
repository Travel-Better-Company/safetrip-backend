package com.safetripbackend.exception;

import com.safetripbackend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {
    /*Manejo de errores de clases iguales creadas*/
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                status.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
    /*Manejo de error de NotFoundException*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                status.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    /*Manejo de excepciones de errores de validaciones */
    @ExceptionHandler(ValidationExpection.class)
    public ResponseEntity<ErrorResponseDto> handleResourceValidationException(ValidationExpection ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                status.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse,status);
    }
    /*Manejo de errores de los argumentos ingresados*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = "Validation error(s) occurred.";

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessage += "|Field: " + fieldError.getField() + " - " + fieldError.getDefaultMessage() + "| ";
        }
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                status.value(),
                errorMessage
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
