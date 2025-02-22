package org.helmes.task.mapper;

import org.helmes.task.dto.SectorDto;
import org.helmes.task.model.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SectorMapper {

  @Mapping(target = "children", expression = "java(mapChildren(sector.getChildren()))")
  SectorDto toDto(Sector sector);

  default List<SectorDto> mapChildren(List<Sector> children) {
    if (children == null) {
      return Collections.emptyList();
    }
    return children.stream().map(this::toDto).collect(Collectors.toList());
  }

  List<SectorDto> toDtoList(List<Sector> sectors);
}
