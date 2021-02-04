package com.tiagoamp.userinfo.controller;

import com.tiagoamp.userinfo.service.UserMapper;
import com.tiagoamp.userinfo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserService service;
    private UserMapper mapper;


    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO requestDTO) {
        var user = mapper.toModel(requestDTO);
        user = service.save(user);
        var responseDTO = mapper.toResponseDTO(user);
        return ResponseEntity.created(URI.create(responseDTO.getId().toString())).body(responseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") Long id) {
        try {
            service.remove(id);
            return ResponseEntity.noContent().build();
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long id) {
        try {
            var user = service.find(id);
            return ResponseEntity.ok(mapper.toResponseDTO(user));
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        var users = service.findAll();
        var responseDTOs = mapper.toResponseDTO(users);
        return ResponseEntity.ok(responseDTOs);
    }

}
