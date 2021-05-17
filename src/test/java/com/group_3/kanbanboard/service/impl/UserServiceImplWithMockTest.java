package com.group_3.kanbanboard.service.impl;

import com.group_3.kanbanboard.entity.UserEntity;
import com.group_3.kanbanboard.enums.UserRole;
import com.group_3.kanbanboard.exception.UserNotFoundException;
import com.group_3.kanbanboard.mappers.UserMapper;
import com.group_3.kanbanboard.mappers.UserMapperImpl;
import com.group_3.kanbanboard.repository.UserRepository;
import com.group_3.kanbanboard.rest.dto.UserResponseDto;
import com.group_3.kanbanboard.rest.dto.UserRequestDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;



import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplWithMockTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String firstName = "Firstname";
    private static final String secondName = "Secondnameov";
    private static final String userName = "FirstSecondnameov";
    private static final String password = "pass";
    private static final String mail = "fs@ya.com";
    private Set<UserRole> role;

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    private UserEntity expectedUser;
    private UserRequestDto expectedUserRequestDto;

    @Before
    public void setUp() {
        role.add(UserRole.ADMIN);
        expectedUser = new UserEntity(ID,firstName,secondName,userName,password,mail,role);
        expectedUserRequestDto = new UserRequestDto(firstName,secondName,password,userName,mail,role);
    }

    @After
    public void tearDown() {
        expectedUser = null;
    }

    @Test
    public void getById() {

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(expectedUser));
        Mockito.when(userMapper.toResponseDto(Mockito.any(UserEntity.class)))
                .thenAnswer(invocation -> new UserMapperImpl().toResponseDto(invocation.<UserEntity>getArgument(0)));
        UserResponseDto actual = userService.getUserById(ID);
        Assert.assertEquals(expectedUser.getFirstName(), actual.getFirstName());
    }

    @Test
    public void getAllUsers() {
        List<UserEntity> expectedListUsers = Collections.singletonList(expectedUser);
        Mockito.when(userMapper.toResponseDto(Mockito.any(UserEntity.class)))
                .thenAnswer(invocation -> new UserMapperImpl().toResponseDto(invocation.<UserEntity>getArgument(0)));
        Mockito.when(userRepository.findAll()).thenReturn(expectedListUsers);
        List<UserResponseDto> actualListUsers = userService.getAllUsers();
        Assert.assertEquals(expectedListUsers.get(0).getFirstName(), actualListUsers.get(0).getFirstName());
    }

    @Test
    public void addUser() {
        Mockito.when(userMapper.toResponseDto(Mockito.any(UserEntity.class)))
                .thenAnswer(invocation -> new UserMapperImpl().toResponseDto(invocation.<UserEntity>getArgument(0)));
        Mockito.when(userMapper.toEntity(Mockito.any(UserRequestDto.class)))
                .thenAnswer(invocation -> new UserMapperImpl().toEntity(invocation.<UserRequestDto>getArgument(0)));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(Optional.of(expectedUser));

        UserResponseDto actualUser = userService.addUser(expectedUserRequestDto);
        System.out.println(actualUser.getRoles());
        Assert.assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());

    }

    @Test
    public void deleteUserById() {
        UUID id = Mockito.any();
        Mockito.when(userRepository.existsById(id)).thenReturn(Boolean.TRUE);
    }
}
