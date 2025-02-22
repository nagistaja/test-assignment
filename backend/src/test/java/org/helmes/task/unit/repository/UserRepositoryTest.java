package org.helmes.task.unit.repository;

import org.helmes.task.model.User;
import org.helmes.task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

  @Mock
  private UserRepository userRepository;

  @Test
  void findByEditToken_ValidToken_ReturnsUser() {
    UUID token = UUID.randomUUID();
    User user = new User();
    user.setEditToken(token);

    when(userRepository.findByEditToken(token)).thenReturn(Optional.of(user));

    Optional<User> result = userRepository.findByEditToken(token);

    assertTrue(result.isPresent());
    assertEquals(token, result.get().getEditToken());
  }

  @Test
  void findByEditToken_InvalidToken_ReturnsEmptyOptional() {
    UUID token = UUID.randomUUID();

    when(userRepository.findByEditToken(token)).thenReturn(Optional.empty());

    Optional<User> result = userRepository.findByEditToken(token);

    assertFalse(result.isPresent());
  }
}