package org.helmes.task.unit.repository;

import org.helmes.task.model.Sector;
import org.helmes.task.repository.SectorRepository;
import org.helmes.task.service.SectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SectorRepositoryTest {

  @Mock
  private SectorRepository sectorRepository;

  @InjectMocks
  private SectorService sectorService;

  @Test
  void findAllTopLevelSectors_ReturnsTopLevelSectors() {
    Sector sector1 = new Sector();
    sector1.setName("Top Level Sector 1");
    Sector sector2 = new Sector();
    sector2.setName("Top Level Sector 2");

    when(sectorRepository.findAllTopLevelSectors()).thenReturn(List.of(sector1, sector2));

    List<Sector> result = sectorService.getAllTopLevelSectors();

    assertEquals(2, result.size());
    assertEquals("Top Level Sector 1", result.get(0).getName());
    assertEquals("Top Level Sector 2", result.get(1).getName());
  }

  @Test
  void findByParentId_ReturnsChildSectors() {
    Sector parent = new Sector();
    parent.setId(1L);
    Sector child1 = new Sector();
    child1.setName("Child Sector 1");
    child1.setParent(parent);
    Sector child2 = new Sector();
    child2.setName("Child Sector 2");
    child2.setParent(parent);

    when(sectorRepository.findByParentId(1L)).thenReturn(List.of(child1, child2));

    List<Sector> result = sectorService.getChildSectors(1L);

    assertEquals(2, result.size());
    assertEquals("Child Sector 1", result.get(0).getName());
    assertEquals("Child Sector 2", result.get(1).getName());
  }

  @Test
  void findByParentId_NoChildSectors_ReturnsEmptyList() {
    when(sectorRepository.findByParentId(1L)).thenReturn(List.of());

    List<Sector> result = sectorService.getChildSectors(1L);

    assertTrue(result.isEmpty());
  }

  @Test
  void findById_ExistingId_ReturnsSector() {
    Sector sector = new Sector();
    sector.setId(1L);
    sector.setName("Existing Sector");

    when(sectorRepository.findById(1L)).thenReturn(Optional.of(sector));

    Optional<Sector> result = sectorService.getSectorById(1L);

    assertTrue(result.isPresent());
    assertEquals("Existing Sector", result.get().getName());
  }

  @Test
  void findById_NonExistingId_ReturnsEmptyOptional() {
    when(sectorRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<Sector> result = sectorService.getSectorById(1L);

    assertFalse(result.isPresent());
  }
}
