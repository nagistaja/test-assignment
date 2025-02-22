package org.helmes.task.mapper;

import org.helmes.task.dto.UserDto;
import org.helmes.task.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

  @Mapping(
          target = "sectorIds",
          expression = "java(user.getSectors() == null ? java.util.Collections.emptyList() : user.getSectors().stream().map(s -> s.getId()).collect(java.util.stream.Collectors.toList()))"
  )
  UserDto toDto(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "editToken", ignore = true)
  @Mapping(target = "sectors", ignore = true)
  void updateUserFromDto(UserDto dto, @MappingTarget User user);
}
