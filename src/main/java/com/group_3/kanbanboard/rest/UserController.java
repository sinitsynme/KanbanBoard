package com.group_3.kanbanboard.rest;


import com.group_3.kanbanboard.rest.dto.UserRequestDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Пользователи")
@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Получить список пользователей")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> listUsers = userService.getAllUsers();
//        UserResponseDto usr = new UserResponseDto("Ivan", "Ivanov", "ivan@soap.com", "developer");
//        UserResponseDto usr1 = new UserResponseDto("Ivan", "Ivanov", "ivan@soap.com", "developer");
//        UserResponseDto usr2 = new UserResponseDto("Ivan", "Ivanov", "ivan@soap.com", "developer");
//        List<UserResponseDto> results = Arrays.asList(usr, usr1, usr2);
        return ResponseEntity.ok().body(listUsers);
    }

    @Operation(summary = "Добавить пользователя")
    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {
       UserResponseDto userResponseDto = userService.addUser(requestDto);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @Operation(summary = "Установка роли пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> setRoleUser(@PathVariable UUID id,
                                                       @RequestBody UserRequestDto releaseRequestDto) {
        return ResponseEntity.ok().body(new UserResponseDto());
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
