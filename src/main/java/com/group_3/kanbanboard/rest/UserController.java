package com.group_3.kanbanboard.rest;


import com.group_3.kanbanboard.rest.dto.UserRequestDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Tag(name = "Пользователи")
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        UserResponseDto usr = new UserResponseDto("Ivan","Ivanov", "ivan@soap.com", "developer");
        UserResponseDto usr1 = new UserResponseDto("Ivan","Ivanov", "ivan@soap.com", "developer");
        UserResponseDto usr2 = new UserResponseDto("Ivan","Ivanov", "ivan@soap.com", "developer");
        List<UserResponseDto> results = Arrays.asList(usr, usr1, usr2);
        return ResponseEntity.ok().body(results);
    }

    @Operation(summary = "Добавить пользователя")
    @PostMapping(value = "/users")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {

        return ResponseEntity.ok().body(new UserResponseDto(requestDto.getFirstName(), requestDto.getSecondName(), requestDto.getRole(), requestDto.getMail()));
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(new UserResponseDto());
    }

    @Operation(summary = "Установка роли пользователя")
    @PutMapping("users/{id}")
    public ResponseEntity<UserResponseDto> setRoleUser(@PathVariable UUID id,
                                                       @RequestBody UserRequestDto releaseRequestDto) {

        return ResponseEntity.ok().body(new UserResponseDto());

    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {

        return ResponseEntity.ok().build();
    }

}
