package com.group_3.kanbanboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.Date;


/**
 * Default Exception handler in Application
 */
@ControllerAdvice
public class AppDefaultExceptionHandler {

    /**
     * @param e - {@link ResourceNotFoundException} throw then missing resource requested.
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 404.
     */
    //Запрошен отсутствующий ресурс(Task, Project, User, Release и т.д )  можно раскидать по отдельным классам
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> exceptionHandler(ResourceNotFoundException e) {
        // лог
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * @param e - {@link AuthenticationException} throw then authentication error .
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 401.
     */
    //Ошибка аутентификации(Spring security)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponse> exceptionHandler(AuthenticationException e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * @param e - {@link AccessDeniedException} throw when there was an attempt to access a limited to access resource.
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 401.
     */
    //Ошибка ограничения доступа(Spring security)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ExceptionResponse> exceptionHandler(AccessDeniedException e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }

    /**
     * @param e - {@link IllegalArgumentException} throw when incorrect method parameters in runtime.
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 405.
     */
    //Неправильные параметры методов в Runtime
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ExceptionResponse> exceptionHandler(IllegalArgumentException e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * @param e - {@link IOException} throw then I / O errors
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 400.
     */
    //Ошибки ввода-вывода
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> exceptionHandler(IOException e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param e - {@link RuntimeException} throw then errors occurrence in runtime
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 400.
     */
    //Другие ошибки Runtime
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> exceptionHandler(RuntimeException e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param e - {@link Exception} called then all other errors
     * @return {@link ResponseEntity} wrapped {@link ExceptionResponse}.
     * Set the {@link HttpStatus} code to 400.
     */
    //Все прочие ошибки
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e) {
        ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}




