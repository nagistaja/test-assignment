package org.helmes.task.unit.service;

import org.helmes.task.dto.SectorDto;
import org.helmes.task.mapper.SectorMapper;
import org.helmes.task.model.Sector;
import org.helmes.task.repository.SectorRepository;
import org.helmes.task.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorServiceTest {

  @Mock
  private SectorRepository sectorRepository;

  @Mock
  private SectorMapper sectorMapper;

  @InjectMocks
  private SectorService sectorService;

  @Test
  void getAllSectorsHierarchical_ReturnsTree() {
    Sector manufacturing = new Sector(1L, "Manufacturing");
    Sector construction = new Sector(4L, "Construction", manufacturing);

    when(sectorRepository.findAllTopLevelSectors()).thenReturn(List.of(manufacturing));
    when(sectorRepository.findByParentId(1L)).thenReturn(List.of(construction));

    when(sectorMapper.toDto(manufacturing))
            .thenReturn(SectorDto.builder().id(1L).name("Manufacturing").build());
    when(sectorMapper.toDto(construction))
            .thenReturn(SectorDto.builder().id(4L).name("Construction").build());

    List<SectorDto> result = sectorService.getAllSectorsHierarchical();

    assertEquals(1, result.size());
    assertEquals("Manufacturing", result.getFirst().getName());

    assertEquals(1, result.getFirst().getChildren().size());
  }

  @Test
  void getAllSectorsHierarchical_NoTopLevelSectors_ReturnsEmptyList() {
    when(sectorRepository.findAllTopLevelSectors()).thenReturn(Collections.emptyList());

    List<SectorDto> result = sectorService.getAllSectorsHierarchical();

    assertEquals(0, result.size());
  }

  @Test
  void getAllSectorsHierarchical_TopLevelSectorWithoutChildren_ReturnsSingleNode() {

    Sector manufacturing = new Sector(1L, "Manufacturing");

    when(sectorRepository.findAllTopLevelSectors()).thenReturn(List.of(manufacturing));
    when(sectorRepository.findByParentId(1L)).thenReturn(Collections.emptyList());

    when(sectorMapper.toDto(manufacturing))
            .thenReturn(SectorDto.builder().id(1L).name("Manufacturing").build());

    List<SectorDto> result = sectorService.getAllSectorsHierarchical();

    assertEquals(1, result.size());
    assertEquals("Manufacturing", result.get(0).getName());
    assertEquals(0, result.get(0).getChildren() == null ? 0 : result.get(0).getChildren().size());
  }

  @Test
  void getAllSectorsFlat_ReturnsAllSectors() {
    Sector manufacturing = new Sector(1L, "Manufacturing");
    Sector construction = new Sector(4L, "Construction", manufacturing);

    when(sectorRepository.findAll()).thenReturn(List.of(manufacturing, construction));

    when(sectorMapper.toDtoList(anyList()))
            .thenReturn(List.of(
                    SectorDto.builder().id(1L).name("Manufacturing").build(),
                    SectorDto.builder().id(4L).name("Construction").build()
            ));

    List<SectorDto> result = sectorService.getAllSectorsFlat();

    assertEquals(2, result.size());
    assertEquals("Manufacturing", result.get(0).getName());
    assertEquals("Construction", result.get(1).getName());
  }
}
