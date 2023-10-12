package com.springmongo.mongospringboot.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof JsonParseException) {
            return duplicateFieldException((JsonParseException) ex.getCause());
        } else if (ex.getCause() instanceof JsonMappingException) {
            return duplicateFieldException((JsonMappingException) ex.getCause());
        } else {
            return irrelevantField((UnrecognizedPropertyException) ex.getCause());
        }
    }

    public ResponseEntity<Object> duplicateFieldException(JsonParseException ex) {
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("message", ex.getOriginalMessage());
        outputMap.put("stacktrace", "Duplicate Field");
        return new ResponseEntity<>(outputMap, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> duplicateFieldException(JsonMappingException ex) {
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("message", ex.getOriginalMessage());
        outputMap.put("stacktrace", "Duplicate Field");
        return new ResponseEntity<>(outputMap, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> irrelevantField(UnrecognizedPropertyException ex) {
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("message", "Irrelevant field " + ex.getPropertyName());
        outputMap.put("status", "Irrelevant field");
        return new ResponseEntity<>(outputMap, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception ex) {
        ex.printStackTrace();
        Map<String, Object> outputMap = new HashMap<>();
        outputMap.put("message", ex.getMessage());
        outputMap.put("stacktrace", ex.getStackTrace());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
