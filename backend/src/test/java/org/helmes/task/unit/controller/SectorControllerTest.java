package org.helmes.task.unit.controller;

import org.helmes.task.controller.SectorController;
import org.helmes.task.dto.SectorDto;
import org.helmes.task.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectorControllerTest {

  @Mock
  private SectorService sectorService;

  @InjectMocks
  private SectorController sectorController;

  @Test
  void getAllSectorsHierarchical_ReturnsListOfSectors() {
    List<SectorDto> sectors = List.of(new SectorDto(), new SectorDto());
    when(sectorService.getAllSectorsHierarchical()).thenReturn(sectors);

    ResponseEntity<List<SectorDto>> response = sectorController.getAllSectorsHierarchical();

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(sectors, response.getBody());
  }

  @Test
  void getAllSectorsHierarchical_ReturnsEmptyList() {
    when(sectorService.getAllSectorsHierarchical()).thenReturn(List.of());

    ResponseEntity<List<SectorDto>> response = sectorController.getAllSectorsHierarchical();

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
  }

  @Test
  void getAllSectorsFlat_ReturnsListOfSectors() {
    List<SectorDto> sectors = List.of(new SectorDto(), new SectorDto());
    when(sectorService.getAllSectorsFlat()).thenReturn(sectors);

    ResponseEntity<List<SectorDto>> response = sectorController.getAllSectorsFlat();

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertEquals(sectors, response.getBody());
  }

  @Test
  void getAllSectorsFlat_ReturnsEmptyList() {
    when(sectorService.getAllSectorsFlat()).thenReturn(List.of());

    ResponseEntity<List<SectorDto>> response = sectorController.getAllSectorsFlat();

    assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
  }
}