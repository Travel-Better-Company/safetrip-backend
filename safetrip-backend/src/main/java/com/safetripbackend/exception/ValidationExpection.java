package com.safetripbackend.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationExpection extends RuntimeException{
    public ValidationExpection(String message){
        super(message);
    }
    public <T> ValidationExpection(String resourceName, Set<ConstraintViolation<T>> violations) {
        super(String.format("Not all constraints satisfied for %s: %s", resourceName,
                violations.stream().map( violation -> String.format("%s %s", violation.getPropertyPath(), violation.getMessage()))
                        .collect(Collectors.joining(". "))));
    }

}
