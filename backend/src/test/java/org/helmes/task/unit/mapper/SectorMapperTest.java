package org.helmes.task.unit.mapper;

import org.helmes.task.dto.SectorDto;
import org.helmes.task.mapper.SectorMapper;
import org.helmes.task.model.Sector;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SectorMapperTest {

  private final SectorMapper sectorMapper = Mappers.getMapper(SectorMapper.class);

  @Test
  void toDto_SectorWithChildren_ReturnsSectorDtoWithChildren() {
    Sector childSector = new Sector();
    childSector.setName("Child Sector");
    Sector parentSector = new Sector();
    parentSector.setName("Parent Sector");
    parentSector.setChildren(List.of(childSector));

    SectorDto sectorDto = sectorMapper.toDto(parentSector);

    assertEquals("Parent Sector", sectorDto.getName());
    assertEquals(1, sectorDto.getChildren().size());
    assertEquals("Child Sector", sectorDto.getChildren().get(0).getName());
  }

  @Test
  void toDto_SectorWithoutChildren_ReturnsSectorDtoWithoutChildren() {
    Sector sector = new Sector();
    sector.setName("Sector");

    SectorDto sectorDto = sectorMapper.toDto(sector);

    assertEquals("Sector", sectorDto.getName());
    assertTrue(sectorDto.getChildren().isEmpty());
  }

  @Test
  void mapChildren_NullChildren_ReturnsEmptyList() {
    List<SectorDto> children = sectorMapper.mapChildren(null);

    assertTrue(children.isEmpty());
  }

  @Test
  void mapChildren_NonNullChildren_ReturnsMappedChildren() {
    Sector childSector = new Sector();
    childSector.setName("Child Sector");

    List<SectorDto> children = sectorMapper.mapChildren(List.of(childSector));

    assertEquals(1, children.size());
    assertEquals("Child Sector", children.get(0).getName());
  }

  @Test
  void toDtoList_EmptyList_ReturnsEmptyDtoList() {
    List<SectorDto> sectorDtos = sectorMapper.toDtoList(Collections.emptyList());

    assertTrue(sectorDtos.isEmpty());
  }

  @Test
  void toDtoList_NonEmptyList_ReturnsMappedDtoList() {
    Sector sector = new Sector();
    sector.setName("Sector");

    List<SectorDto> sectorDtos = sectorMapper.toDtoList(List.of(sector));

    assertEquals(1, sectorDtos.size());
    assertEquals("Sector", sectorDtos.get(0).getName());
  }
}
