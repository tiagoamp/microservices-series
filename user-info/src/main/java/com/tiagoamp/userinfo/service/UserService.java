package com.tiagoamp.userinfo.service;

import com.tiagoamp.userinfo.domain.User;
import com.tiagoamp.userinfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository repository;
    private UserMapper mapper;


    public User save(User user) {
        var entity = mapper.toEntity(user);
        entity = repository.save(entity);
        return mapper.toModel(entity);
    }

    public void remove(Long id) {
        var user = find(id);
        repository.deleteById(user.getId());
    }

    public User find(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new NoResultException());
        return mapper.toModel(entity);
    }

    public List<User> findAll() {
        var entities = repository.findAll();
        return mapper.toModel(entities);
    }

}
