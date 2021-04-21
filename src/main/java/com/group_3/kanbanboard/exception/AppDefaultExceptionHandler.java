package com.group_3.kanbanboard.exception;

import com.group_3.kanbanboard.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.security.core.AuthenticationException;

@ControllerAdvice
public class AppDefaultExceptionHandler {

    //Запрошен отсутствующий ресурс(Task, Project, User, Release и т.д )  можно раскидать по отдельным классам
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> exceptionHandler(ResourceNotFoundException e) {
        return null;
        // лог
        // обработка
    }

    //Ошибка аутентификации(Spring security)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> exceptionHandler(AuthenticationException e) {
        return null;

    }

    //Ошибка ограничения доступа(Spring security)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> exceptionHandler(AccessDeniedException e) {
        return null;

    }

    //Неправильные параметры методов в Runtime
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<Object> exceptionHandler(IllegalArgumentException e) {
        return null;

    }

    //Другие ошибки Runtime
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> exceptionHandler(RuntimeException e) {
        return null;
    }

    //Все прочие ошибки
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        return null;
    }
}




