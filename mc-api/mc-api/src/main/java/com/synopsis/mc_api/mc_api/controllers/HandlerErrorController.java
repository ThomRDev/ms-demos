package com.synopsis.mc_api.mc_api.controllers;

import com.synopsis.mc_api.mc_api.exceptions.ExternalProductException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerErrorController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleValidateDtoExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        Map<String,Object> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        response.put("ok",false);
        response.put("msg",errors );
        return response;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExternalProductException.class)
    public Map<String,Object> handlePersonaExceptions(ExternalProductException ex){
        Map<String,Object> errors = new HashMap<>();
        errors.put("ok",false);
        errors.put("msg",ex.getMessage());
        return errors;
    }


}