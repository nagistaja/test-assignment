package org.helmes.task.service;

import lombok.RequiredArgsConstructor;
import org.helmes.task.dto.SectorDto;
import org.helmes.task.mapper.SectorMapper;
import org.helmes.task.model.Sector;
import org.helmes.task.repository.SectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {

  private final SectorRepository sectorRepository;
  private final SectorMapper sectorMapper;

  public List<SectorDto> getAllSectorsHierarchical() {
    List<Sector> topLevel = sectorRepository.findAllTopLevelSectors();
    return topLevel.stream()
            .map(this::buildHierarchy)
            .collect(Collectors.toList());
  }

  public List<SectorDto> getAllSectorsFlat() {
    return sectorMapper.toDtoList(sectorRepository.findAll());
  }

  public List<Sector> getAllTopLevelSectors() {
    return sectorRepository.findAllTopLevelSectors();
  }

  public List<Sector> getChildSectors(Long parentId) {
    return sectorRepository.findByParentId(parentId);
  }

  public Optional<Sector> getSectorById(Long id) {
    return sectorRepository.findById(id);
  }

  private SectorDto buildHierarchy(Sector sector) {
    SectorDto dto = sectorMapper.toDto(sector);
    List<Sector> children = sectorRepository.findByParentId(sector.getId());
    dto.setChildren(children.stream()
            .map(this::buildHierarchy)
            .toList());
    return dto;
  }
}
