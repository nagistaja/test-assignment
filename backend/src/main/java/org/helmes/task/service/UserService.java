package org.helmes.task.service;

import lombok.RequiredArgsConstructor;
import org.helmes.task.dto.UserDto;
import org.helmes.task.mapper.UserMapper;
import org.helmes.task.model.Sector;
import org.helmes.task.model.User;
import org.helmes.task.repository.SectorRepository;
import org.helmes.task.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final SectorRepository sectorRepository; // Must be final!

  public UUID saveOrUpdateUser(UserDto userDto) {
    Optional<User> existingUserOpt = (userDto.getEditToken() != null)
            ? userRepository.findByEditToken(userDto.getEditToken())
            : Optional.empty();
    User user = existingUserOpt.orElse(new User());

    updateUserFromDto(userDto, user);

    if (user.getEditToken() == null) {
      user.setEditToken(UUID.randomUUID());
    }
    userRepository.save(user);
    return user.getEditToken();
  }

  public UserDto getUserByToken(UUID token) {
    User user = userRepository.findByEditToken(token)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return userMapper.toDto(user);
  }

  public void updateUser(String token, UserDto userDto) {
    User user = userRepository.findByEditToken(UUID.fromString(token))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    updateUserFromDto(userDto, user);

    userRepository.save(user);
  }

  private void updateUserFromDto(UserDto userDto, User user) {
    userMapper.updateUserFromDto(userDto, user);

    if (userDto.getSectorIds() != null && !userDto.getSectorIds().isEmpty()) {
      Set<Sector> sectors = userDto.getSectorIds().stream()
              .map(sectorId -> sectorRepository.findById(sectorId)
                      .orElseThrow(() ->
                              new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sector ID: " + sectorId)))
              .collect(Collectors.toSet());
      user.setSectors(sectors);
    } else {
      user.setSectors(null);
    }
  }
}
