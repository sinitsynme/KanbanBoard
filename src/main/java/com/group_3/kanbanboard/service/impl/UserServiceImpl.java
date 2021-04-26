package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.exception.UserNotFoundException;
import com.group_3.kanbanboard.repository.UserRepository;
import com.group_3.kanbanboard.rest.dto.UserRequestDto;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto getUserById(UUID id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setFirstName(user.getFirstName());
        responseDto.setSecondName(user.getSecondName());
        responseDto.setMail(user.getMail());
        responseDto.setRole(user.getRole().toString());
        return responseDto;
    }

    @Transactional
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return null;
    }

    @Transactional
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        UserEntity user = new UserEntity();
        userRepository.save(user);

        return null;
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setSecondName (userRequestDto.getSecondName());

        userRepository.save(userEntity);
        return null;
    }

    @Transactional
    @Override
    public void deleteUserById(UUID id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}

