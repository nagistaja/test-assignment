package org.helmes.task.integration.api.repository;

import org.helmes.task.integration.BaseIntegrationTest;
import org.helmes.task.model.Sector;
import org.helmes.task.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SectorRepositoryIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private SectorRepository sectorRepository;

  @Test
  public void testFindAllTopLevelSectors() {
    Sector top1 = new Sector();
    top1.setName("Top Sector 1");
    Sector top2 = new Sector();
    top2.setName("Top Sector 2");
    sectorRepository.save(top1);
    sectorRepository.save(top2);

    List<Sector> topSectors = sectorRepository.findAllTopLevelSectors();
    assertThat(topSectors).isNotEmpty();
    assertThat(topSectors)
            .extracting(Sector::getName)
            .contains("Top Sector 1", "Top Sector 2");
  }

  @Test
  public void testFindByParentId() {
    Sector parent = new Sector();
    parent.setName("Parent Sector");
    parent = sectorRepository.save(parent);

    Sector child1 = new Sector();
    child1.setName("Child Sector 1");
    child1.setParent(parent);
    Sector child2 = new Sector();
    child2.setName("Child Sector 2");
    child2.setParent(parent);
    sectorRepository.save(child1);
    sectorRepository.save(child2);

    List<Sector> children = sectorRepository.findByParentId(parent.getId());
    assertThat(children).hasSize(2);
    assertThat(children).extracting(Sector::getName)
            .containsExactlyInAnyOrder("Child Sector 1", "Child Sector 2");
  }

  @Test
  public void testFindById() {
    Sector sector = new Sector();
    sector.setName("Test Sector");
    Sector saved = sectorRepository.save(sector);

    Optional<Sector> found = sectorRepository.findById(saved.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Test Sector");
  }
}
