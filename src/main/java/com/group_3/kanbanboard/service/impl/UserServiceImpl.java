package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.enums.UserRole;
import com.group_3.kanbanboard.exception.UserNotFoundException;
import com.group_3.kanbanboard.mappers.UserMapper;
import com.group_3.kanbanboard.repository.UserRepository;
import com.group_3.kanbanboard.rest.dto.UserRequestDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.service.UserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserResponseDto getUserById(UUID id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        return userMapper.toResponseDto(user);
    }

    @Transactional
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) throws UserNotFoundException {
        UserEntity user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);

        return userMapper.toResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setSecondName(userRequestDto.getSecondName());

        userRepository.save(userEntity);
        return userMapper.toResponseDto(userEntity);
    }

    @Transactional
    @Override
    public void deleteUserById(UUID id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void addAdmin(){
        UserEntity userEntity =  new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("$2y$08$gFrPon1/FCbSEWFYPXMtX.yBDltLs4WbdLad3MFzQHmqmHfkM6mia");
        userEntity.setRoles(Collections.singleton(UserRole.LEAD));
        userRepository.save(userEntity);
    }
}

