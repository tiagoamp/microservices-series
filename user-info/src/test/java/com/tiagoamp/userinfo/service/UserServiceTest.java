package com.tiagoamp.userinfo.service;

import com.tiagoamp.userinfo.domain.User;
import com.tiagoamp.userinfo.repository.UserEntity;
import com.tiagoamp.userinfo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repo;

    @Spy  // injects this specific instance to target class
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserService service;


    @DisplayName("Should create user")
    @Test
    void save() {
        var entity = new UserEntity(1L, "name", "email@email.com", LocalDate.now().minusYears(30), "address");
        Mockito.when(repo.save(Mockito.any(UserEntity.class))).thenReturn(entity);
        var result = service.save(new User());
        assertTrue(result.getId() != null);
    }

    @DisplayName("Should remove user")
    @Test
    void remove() {
        try {
            var entity = new UserEntity(1L, "name", "email@email.com", LocalDate.now().minusYears(30), "address");
            Mockito.when(repo.findById(entity.getId())).thenReturn(Optional.of(entity));
            service.remove(entity.getId());
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }

    @DisplayName("Should throw exception when user id does not exist")
    @Test
    void remove_throwException() {
        Mockito.when(repo.findById(Mockito.anyLong())).thenThrow(NoResultException.class);
        assertThrows(NoResultException.class, () -> service.remove(1L));
    }

    @DisplayName("Should return user by id when it exists")
    @Test
    void find() {
        var entity = new UserEntity(1L, "name", "email@email.com", LocalDate.now().minusYears(30), "address");
        Mockito.when(repo.findById(entity.getId())).thenReturn(Optional.of(entity));
        var result = service.find(entity.getId());
        assertEquals(result.getId(), entity.getId());
    }

    @DisplayName("Should throw exception when id does not exists")
    @Test
    void find_throwException() {
        Mockito.when(repo.findById(Mockito.anyLong())).thenThrow(NoResultException.class);
        assertThrows(NoResultException.class, () -> service.find(1L));
    }

    @DisplayName("Should return user list")
    @Test
    void findAll() {
        var entitiesList = List.of(new UserEntity(1L, "name", "email@email.com", LocalDate.now().minusYears(30), "address"),
                new UserEntity(2L, "name 2", "email2@email.com", LocalDate.now().minusYears(30), "address"));
        Mockito.when(repo.findAll()).thenReturn(entitiesList);
        var result = service.findAll();
        assertEquals(entitiesList.size(), result.size());
    }

    @DisplayName("Should return empty list when does not have registered users")
    @Test
    void findAll_throwsException() {
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());
        var result = service.findAll();
        assertTrue(result.isEmpty());
    }

}