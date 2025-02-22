package org.helmes.task.unit.service;

import org.helmes.task.dto.UserDto;
import org.helmes.task.mapper.UserMapper;
import org.helmes.task.model.Sector;
import org.helmes.task.model.User;
import org.helmes.task.repository.SectorRepository;
import org.helmes.task.repository.UserRepository;
import org.helmes.task.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private SectorRepository sectorRepository;

  @Mock
  private UserMapper userMapper;

  @InjectMocks
  private UserService userService;

  @Test
  void saveOrUpdateUser_NewUser_ReturnsNewToken() {
    UserDto userDto = UserDto.builder()
            .name("Test User")
            .sectorIds(java.util.List.of(1L))
            .agreeToTerms(true)
            .build();

    when(sectorRepository.findById(1L))
            .thenReturn(Optional.of(new Sector(1L, "Dummy Sector")));

    when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

    UUID token = userService.saveOrUpdateUser(userDto);
    assert token != null;
  }

  @Test
  void saveOrUpdateUser_InvalidSector_ThrowsException() {
    UserDto userDto = UserDto.builder()
            .name("Test User")
            .sectorIds(java.util.List.of(999L))  // Non-existent sector
            .agreeToTerms(true)
            .build();

    when(sectorRepository.findById(999L))
            .thenReturn(Optional.empty());

    assertThrows(ResponseStatusException.class, () -> userService.saveOrUpdateUser(userDto));
  }
}
