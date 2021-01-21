package com.tiagoamp.userinfo.controller;

import com.tiagoamp.userinfo.domain.User;
import com.tiagoamp.userinfo.service.UserMapper;
import com.tiagoamp.userinfo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserController controller;

    private UserRequestDTO requestDTO = new UserRequestDTO("name", "email@email.com", LocalDate.now().minusYears(30), "address");
    private User user = new User(1L, "name", "email@email.com", LocalDate.now().minusYears(30), "address");


    @Test
    void create() {
        Mockito.when(service.save(Mockito.any(User.class))).thenReturn(user);
        var result = controller.create(requestDTO);
        assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
        assertEquals(user.getId(), result.getBody().getId());
    }

    @Test
    void remove() {
        var result = controller.remove(user.getId());
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCode().value());
        assertNull(result.getBody());
    }

    @Test
    void findById() {
        Mockito.when(service.find(Mockito.anyLong())).thenReturn(user);
        var result = controller.findById(user.getId());
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        assertEquals(user.getId(), result.getBody().getId());
    }

    @Test
    void findAll() {
        Mockito.when(service.findAll()).thenReturn(List.of(user));
        var result = controller.findAll();
        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        assertEquals(1, result.getBody().size());
    }

}