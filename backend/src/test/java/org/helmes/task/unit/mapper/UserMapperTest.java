package org.helmes.task.unit.mapper;

import org.helmes.task.dto.UserDto;
import org.helmes.task.mapper.UserMapper;
import org.helmes.task.model.Sector;
import org.helmes.task.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserMapperTest {

  private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Test
  void toDto_UserWithSectors_ReturnsUserDtoWithSectorIds() {
    User user = new User();
    user.setSectors(Set.of(new Sector(1L, "One"), new Sector(2L, "Two")));

    UserDto userDto = userMapper.toDto(user);

    assertEquals(2, userDto.getSectorIds().size());
    assertTrue(userDto.getSectorIds().containsAll(List.of(1L, 2L)));
  }

  @Test
  void toDto_UserWithoutSectors_ReturnsUserDtoWithEmptySectorIds() {
    User user = new User();
    user.setSectors(null);

    UserDto userDto = userMapper.toDto(user);

    assertTrue(userDto.getSectorIds().isEmpty());
  }

  @Test
  void updateUserFromDto_ValidDto_UpdatesUser() {
    UserDto userDto = new UserDto();
    userDto.setName("Updated Name");
    User user = new User();
    user.setName("Old Name");

    userMapper.updateUserFromDto(userDto, user);

    assertEquals("Updated Name", user.getName());
  }

  @Test
  void updateUserFromDto_DtoWithNullValues_DoesNotUpdateUser() {
    UserDto userDto = new UserDto();
    User user = new User();
    user.setName("Old Name");

    userMapper.updateUserFromDto(userDto, user);

    assertEquals("Old Name", user.getName());
  }
}